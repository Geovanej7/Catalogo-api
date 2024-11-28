package com.catalogo.catalogo_api.service.impl;

import com.catalogo.catalogo_api.model.Admin;
import com.catalogo.catalogo_api.model.emails.Email;
import com.catalogo.catalogo_api.model.emails.EmailRepository;
import com.catalogo.catalogo_api.model.emails.EmailService;
import com.catalogo.catalogo_api.repository.AdminRepository;
import com.catalogo.catalogo_api.service.AdminService;
import com.catalogo.catalogo_api.util.exeptions.AdminException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private EmailRepository emailRepository;


    @Transactional
    public Admin create(Admin admin) {
        
        admin.setEnabled(Boolean.TRUE);
        admin.setVersion(1L);
        admin.setCreationDate(LocalDate.now());
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
        adm.setLastModifiedDate(LocalDate.now());
        adm.setVersion(adm.getVersion()+1);
        adminRepository.save(adm);
    }

    @Transactional
    public void sendEmailPasswordReset(Admin admin) {

        Email email = new Email();
        email.setAdmin(admin);
        email.setUuid(UUID.randomUUID());
        email.setExpirationDate(Instant.now().plusMillis(900000));
        emailRepository.save(email);
        
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("token", email.getUuid());
        parameters.put("admin", admin);

        try {
            emailService.sendEmailPasswordReset(admin, parameters);
        } catch (Exception e) {
            throw new AdminException("Error sending email.");
        }

    }


    @Transactional
    public String resetPassword(String token, String newPassword, String confirmPassword){
        
        if (!newPassword.equals(confirmPassword)) {
            throw new AdminException("password does not match");
        }else{
            Email emailPassword = emailRepository.findByUuid(UUID.fromString(token));

            if(emailPassword != null){
                if(emailPassword.getExpirationDate().compareTo(Instant.now())>=0){
                    emailPassword.getAdmin().setPassword(newPassword);
                    adminRepository.save(emailPassword.getAdmin());
                    emailRepository.delete(emailPassword);
                    return "new password";
                } else {
                    throw new AdminException("token invalid");
                }
            }else {
                throw new AdminException("token invalid");
            }
        }

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
