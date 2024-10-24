package com.catalogo.catalogo_api.service;

import java.util.List;

import com.catalogo.catalogo_api.model.Admin;

public interface AdminService {

    Admin create(Admin adminToCreate);

    Admin findById(Long id);

    List<Admin> findAll();

    void update(Long id, Admin newAdmin);

    void delete(Long id);

}
