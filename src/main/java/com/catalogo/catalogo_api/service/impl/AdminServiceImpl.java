package com.catalogo.catalogo_api.service.impl;

import com.catalogo.catalogo_api.domain.model.Admin;
import com.catalogo.catalogo_api.domain.repository.AdminRepository;
import com.catalogo.catalogo_api.service.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    /*public AdminServiceImpl(AdminRepository adminRepository){
        this.adminRepository = adminRepository;
    }*/

    @Override
    public Admin create(Admin adminToCreate) {
        adminToCreate.setEnabled(Boolean.TRUE);
        adminToCreate.setVersion(1L);
        adminToCreate.setCreationDate(LocalDate.now());
        return adminRepository.save(adminToCreate);
    }

    @Override
    public Admin findById(Long id) {
        return adminRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<Admin> findAll() {
        return List.of();
    }
}
