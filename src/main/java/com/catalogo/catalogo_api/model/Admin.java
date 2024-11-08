package com.catalogo.catalogo_api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;
import org.hibernate.annotations.SQLRestriction;

import com.catalogo.catalogo_api.model.access.User;
import com.catalogo.catalogo_api.util.entity.AuditableEntity;

@Entity(name = "tb_admin")
@SQLRestriction("enabled = true")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Admin extends AuditableEntity {

   @ManyToOne
   @JoinColumn(name = "usuario_id", nullable = false)
   private User user;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String linkInstagram;

    @OneToMany(mappedBy ="admin", orphanRemoval = true ,fetch = FetchType.EAGER)
    private List<Card> cards;
}
