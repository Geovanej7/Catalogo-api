package com.catalogo.catalogo_api.service.impl;

import com.catalogo.catalogo_api.model.Admin;
import com.catalogo.catalogo_api.repository.AdminRepository;
import com.catalogo.catalogo_api.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;


@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public Admin create(Admin adminToCreate) {
        adminToCreate.setEnabled(Boolean.TRUE);
        adminToCreate.setVersion(1L);
        adminToCreate.setCreationDate(LocalDate.now());
        return adminRepository.save(adminToCreate);
    }

    @Override
    public Admin findById(Long id) {
        
        return adminRepository.findById(id).get();
    }

    @Override
    public List<Admin> findAll() {

        return adminRepository.findAll();
    }

    @Override
    public void update(Long id, Admin newAdmin) {
        
        Admin adm = adminRepository.findById(id).get();
        adm.setPhone(newAdmin.getPhone());
        adm.setPassword(newAdmin.getPassword());
        adm.setEmail(newAdmin.getEmail());
        adm.setLastModifiedDate(LocalDate.now());
        adm.setVersion(adm.getVersion()+1);
        adminRepository.save(adm);
    }

    @Override
    public void delete(Long id) {
       
        Admin adm = adminRepository.findById(id).get();
        adm.setEnabled(Boolean.FALSE);
        adm.setLastModifiedDate(LocalDate.now());
        adm.setVersion(adm.getVersion()+1);
        adminRepository.save(adm);
    }
}
