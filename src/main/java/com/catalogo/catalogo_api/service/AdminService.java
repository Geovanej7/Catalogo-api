package com.catalogo.catalogo_api.service;

import com.catalogo.catalogo_api.domain.model.Admin;

import java.util.List;

public interface AdminService {

    Admin create(Admin adminToCreate);

    Admin findById(Long id);

    List<Admin> findAll();

}
