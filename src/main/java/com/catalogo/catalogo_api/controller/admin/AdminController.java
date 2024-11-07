package com.catalogo.catalogo_api.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import com.catalogo.catalogo_api.model.Admin;
import com.catalogo.catalogo_api.service.AdminService;
import org.springframework.ui.Model;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
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

    @Autowired
    private SpringTemplateEngine templateEngine;

    @Operation(summary = "Create a new admin", description = "Service to create a new admin user.")
    @PostMapping
    public ResponseEntity<Admin> create(@RequestBody @Valid AdminRequest request){
        Admin admin = adminService.create(request.build());
        return new ResponseEntity<Admin>(admin,HttpStatus.CREATED);
    }

    @Operation(summary = "Find all admins", description = "Service to retrieve all admin users.")
    @GetMapping
    public List<Admin> findAll(){
        return adminService.findAll();
    }

    @Operation(summary = "Find admin by ID", description = "Service to retrieve an admin user by their ID.")
    @GetMapping("/{id}")
    public Admin findById(@PathVariable Long id){
        return adminService.findById(id);
    }

    @Operation(summary = "Update an admin by ID", description = "Service to update an existing admin user by their ID.")
    @PutMapping("/{id}")
    public ResponseEntity<Admin> update(@PathVariable("id") Long id,@RequestBody AdminRequest request){
        adminService.update(id,request.build());
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "reset password by email", description = "service to change user password by email.")
    @PostMapping("/request-password")
    public ResponseEntity<String> handlePasswordReset(@RequestBody PasswordResetRequest email){
        Admin adm = adminService.findUserByEmail(email.getEmail());
        if (adm != null){
            adminService.sendEmailPasswordReset(adm);
            return ResponseEntity.ok("Password reset email sent.");
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Admin not found.");
        }
    }

    @Operation(summary = "render password form", description = "service to render the page where the user can change their password")
    @GetMapping("/send-template")
    public String showPasswordResetForm(@RequestParam("token") String token, Model model) {

        Context context = new Context();
        context.setVariable("token", token);
        System.out.println(token + "1111111111111111111111111");
        String htmlContent = templateEngine.process("passwordResetForm", context);
        model.addAttribute("htmlContent", htmlContent);
        return htmlContent;
    }

    @Operation(summary = "", description = "password reset service.")
    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestParam("token") String token,
            @RequestParam("newPassword") String newPassword,
            @RequestParam("confirmPassword") String confirmPassword){
                
                if (!newPassword.equals(confirmPassword)) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Passwords do not match.");
                }

                System.out.println(token + "22222222222222222222222222222");
            
                try {
                    adminService.resetPassword(token, newPassword, confirmPassword);
                    return ResponseEntity.ok("Password reset successfully.");
                } catch (Exception e) {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error resetting password. Please try again.");
                }
            }

    @Operation(summary = "Delete an admin by ID", description = "Service to delete an existing admin user by their ID.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        adminService.delete(id);
        return ResponseEntity.ok().build();
    }
    
}
