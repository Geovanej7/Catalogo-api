package com.catalogo.catalogo_api.service;
import java.util.List;
import com.catalogo.catalogo_api.model.Admin;

public interface AdminService {

    Admin create(Admin adminToCreate);

    Admin findById(Long id);

    List<Admin> findAll();

    Admin findUserByEmail(String email);

    void update(Long id, Admin newAdmin);
    
    void SendEmail(Admin admin);

    String resetPassword(String email, String newPassword, String confirmPassword);

    void delete(Long id);

}
