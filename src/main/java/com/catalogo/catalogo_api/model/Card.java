package com.catalogo.catalogo_api.model;

import com.catalogo.catalogo_api.model.util.AuditableEntity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "tb_card")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Card extends AuditableEntity {

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
