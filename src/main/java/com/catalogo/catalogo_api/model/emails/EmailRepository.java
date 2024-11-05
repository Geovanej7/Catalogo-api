package com.catalogo.catalogo_api.model.emails;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<Email, Long> {
    
    Email findByUuid(UUID uuid);
}
