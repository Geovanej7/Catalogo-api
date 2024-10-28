package com.catalogo.catalogo_api.util.entity;

import java.time.LocalDate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public class AuditableEntity extends BusinessEntity {

    @JsonIgnore
    @Version
    private Long version;

    @JsonIgnore
    @CreatedDate
    private LocalDate creationDate;
   
    @JsonIgnore
    @LastModifiedDate
    private LocalDate lastModifiedDate;
}
