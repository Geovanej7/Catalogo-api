package com.catalogo.catalogo_api.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import com.catalogo.catalogo_api.model.Admin;
import com.catalogo.catalogo_api.service.AdminService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/controller/admin")
@CrossOrigin
public class AdminController {
    
    @Autowired
    private AdminService adminService;

    @PostMapping
    public ResponseEntity<Admin> create(@RequestBody AdminRequest request){
        Admin admin = adminService.create(request.build());
        return new ResponseEntity<Admin>(admin,HttpStatus.CREATED);
    }

    @GetMapping
    public List<Admin> findAll(){
        return adminService.findAll();
    }

    @GetMapping("/{id}")
    public Admin findById(@PathVariable Long id){
        return adminService.findById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Admin> update(@PathVariable("id") Long id,@RequestBody AdminRequest request){
        adminService.update(id,request.build());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        adminService.delete(id);
        return ResponseEntity.ok().build();
    }
    
}
