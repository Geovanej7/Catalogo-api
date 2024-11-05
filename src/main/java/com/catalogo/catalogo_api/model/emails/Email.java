package com.catalogo.catalogo_api.model.emails;

import java.time.Instant;
import java.util.UUID;

import com.catalogo.catalogo_api.model.Admin;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_emails")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Email {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private UUID uuid;

    @Column(nullable = false)
    private Instant expirationDate;

    @ManyToOne
    @JoinColumn(name = "Id_admin", referencedColumnName = "id")
    private Admin admin;
}
