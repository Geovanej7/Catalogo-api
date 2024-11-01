package com.catalogo.catalogo_api.service.impl;

import com.catalogo.catalogo_api.model.Admin;
import com.catalogo.catalogo_api.model.emails.EmailService;
import com.catalogo.catalogo_api.repository.AdminRepository;
import com.catalogo.catalogo_api.service.AdminService;
import com.catalogo.catalogo_api.util.exeptions.AdminException;
import org.springframework.security.crypto.password.PasswordEncoder;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public Admin create(Admin admin) {
        admin.setEnabled(Boolean.TRUE);
        admin.setVersion(1L);
        admin.setCreationDate(LocalDate.now());
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        emailService.sendEmailWelcome(admin);
        return adminRepository.save(admin);
    }

    @Transactional
    public Admin findById(Long id) {
        
        return adminRepository.findById(id)
        .orElseThrow(() -> AdminException.notFound(id));
    }
    
    @Transactional
    public Admin findUserByEmail(String email) {
        return adminRepository.findByEmail(email)
        .orElseThrow(() -> AdminException.emailNotFound(email));
    }

    @Transactional
    public List<Admin> findAll() {

        return adminRepository.findAll();
    }

    @Transactional
    public void update(Long id, Admin newAdmin) {
        
        Admin adm = adminRepository.findById(id)
        .orElseThrow(() -> AdminException.notFound(id));

        adm.setPhone(newAdmin.getPhone());
        adm.setPassword(newAdmin.getPassword());
        adm.setEmail(newAdmin.getEmail());
        adm.setLastModifiedDate(LocalDate.now());
        adm.setVersion(adm.getVersion()+1);
        adminRepository.save(adm);
    }

    @Transactional
    public String resetPassword(String email, String newPassword, String confirmPassword){
        Optional<Admin> adm = adminRepository.findByEmail(email);
        Admin admin = adm.get();
        admin.setPassword(passwordEncoder.encode(newPassword));
        adminRepository.save(admin); 
        return "reset password ok";

    }

    @Transactional
    public void delete(Long id) {
       
        Admin adm = adminRepository.findById(id)
        .orElseThrow(() -> AdminException.notFound(id));
        adm.setEnabled(Boolean.FALSE);
        adm.setLastModifiedDate(LocalDate.now());
        adm.setVersion(adm.getVersion()+1);
        adminRepository.save(adm);
    }
}
