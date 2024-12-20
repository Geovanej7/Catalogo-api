package com.catalogo.catalogo_api.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Catalogo Online API")
                        .version("1.0")
                        .description("API para o projeto Catalogo Online")
                        .contact(new Contact()
                                .name("Geovane José")
                                .email("gjsj@discente.ifpe.edu.br")));
    }

    @Bean
    public GroupedOpenApi customApi() {
        return GroupedOpenApi.builder()
                .group("controller")
                .pathsToMatch("/controller/**")
                .pathsToExclude("/error", "/actuator/**")
                .build();
    }
}
