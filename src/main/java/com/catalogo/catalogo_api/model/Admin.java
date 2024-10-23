package com.catalogo.catalogo_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

import com.catalogo.catalogo_api.model.util.AuditableEntity;

@Entity(name = "tb_admin")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Admin extends AuditableEntity {

    @Column(nullable = false, unique = true)
    @Email
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String phone;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Card> cards;
}
