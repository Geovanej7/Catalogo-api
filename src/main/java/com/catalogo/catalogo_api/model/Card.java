package com.catalogo.catalogo_api.model;

import org.hibernate.annotations.SQLRestriction;

import com.catalogo.catalogo_api.util.entity.AuditableEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "tb_card")
@SQLRestriction("enabled = true")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Card extends AuditableEntity {

    @ManyToOne
    @JsonIgnore
    private Admin admin;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    @Min(0)
    private Double price;

    @Column(length = 255,nullable = false)
    private String description;

    @Column(nullable = false)
    private String image;
}
