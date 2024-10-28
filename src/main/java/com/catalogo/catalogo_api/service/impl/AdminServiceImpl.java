package com.catalogo.catalogo_api.service.impl;

import com.catalogo.catalogo_api.model.Admin;
import com.catalogo.catalogo_api.repository.AdminRepository;
import com.catalogo.catalogo_api.service.AdminService;
import com.catalogo.catalogo_api.util.exeptions.AdminException;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;


@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Transactional
    public Admin create(Admin adminToCreate) {
        adminToCreate.setEnabled(Boolean.TRUE);
        adminToCreate.setVersion(1L);
        adminToCreate.setCreationDate(LocalDate.now());
        return adminRepository.save(adminToCreate);
    }

    @Transactional
    public Admin findById(Long id) {
        
        return adminRepository.findById(id)
        .orElseThrow(() -> AdminException.notFound(id));
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
    public void delete(Long id) {
       
        Admin adm = adminRepository.findById(id)
        .orElseThrow(() -> AdminException.notFound(id));
        adm.setEnabled(Boolean.FALSE);
        adm.setLastModifiedDate(LocalDate.now());
        adm.setVersion(adm.getVersion()+1);
        adminRepository.save(adm);
    }
}
