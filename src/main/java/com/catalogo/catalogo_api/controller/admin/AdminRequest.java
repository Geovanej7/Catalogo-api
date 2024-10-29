package com.catalogo.catalogo_api.controller.admin;

import com.catalogo.catalogo_api.model.Admin;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminRequest {

    @NotBlank(message = "O email e de preenchimento obrigatorio")
    @Email
    private String email;

    @NotBlank(message = "A senha e de preenchimento obrigatorio")
    private String password;

    @NotBlank(message = "O telefone e de preenchimento obrigatorio")
    private String phone;

    public Admin build(){

        return Admin.builder()
        .email(email)
        .password(password)
        .phone(phone)
        .build();

    }
}
