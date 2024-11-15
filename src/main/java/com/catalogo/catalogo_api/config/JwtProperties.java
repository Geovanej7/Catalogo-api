package com.catalogo.catalogo_api.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ConfigurationProperties(prefix = "spring.security.jwt")
@Component
@Getter
@Setter
@NoArgsConstructor
@ToString
public class JwtProperties {
    private String secretKey;
    private Long expirationTime;

}
