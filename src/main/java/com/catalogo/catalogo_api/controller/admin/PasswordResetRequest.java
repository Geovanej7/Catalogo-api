package com.catalogo.catalogo_api.controller.admin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class PasswordResetRequest {
    private String email;

    public PasswordResetRequest build(){
        return PasswordResetRequest.builder()
                .email(email)
                .build();
    }

}
