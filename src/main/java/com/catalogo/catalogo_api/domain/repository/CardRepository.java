package com.catalogo.catalogo_api.domain.repository;

import com.catalogo.catalogo_api.domain.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card,Long> {
}
