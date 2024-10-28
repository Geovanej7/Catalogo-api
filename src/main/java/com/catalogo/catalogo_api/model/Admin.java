package com.catalogo.catalogo_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;
import org.hibernate.annotations.SQLRestriction;

import com.catalogo.catalogo_api.util.entity.AuditableEntity;

@Entity(name = "tb_admin")
@SQLRestriction("enabled = true")
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

    @OneToMany(mappedBy ="admin", orphanRemoval = true ,fetch = FetchType.EAGER)
    private List<Card> cards;
}
