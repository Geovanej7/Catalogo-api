package com.catalogo.catalogo_api.controller.admin;

import java.util.Arrays;

import com.catalogo.catalogo_api.model.Admin;
import com.catalogo.catalogo_api.model.access.User;

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

    @NotBlank(message = "O Link do instagram e de preenchimento obrigatorio")
    private String linkInstagram;

    public User buildUser() {
        return User.builder()
            .username(email)
            .password(password)
            .roles(Arrays.asList(User.ROLE_ADMIN))
            .build();
    }

    public Admin build(){

        return Admin.builder()
        .user(buildUser())
        .phone(phone)
        .linkInstagram(linkInstagram)
        .build();

    }
}
