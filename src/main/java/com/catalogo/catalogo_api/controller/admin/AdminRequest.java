package com.catalogo.catalogo_api.controller.admin;

import com.catalogo.catalogo_api.model.Admin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminRequest {
    
    private String email;

    private String password;

    private String phone;

    public Admin build(){

        return Admin.builder()
        .email(email)
        .password(password)
        .phone(phone)
        .build();

    }
}
