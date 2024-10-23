package com.catalogo.catalogo_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.catalogo.catalogo_api.model.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Long> {
}