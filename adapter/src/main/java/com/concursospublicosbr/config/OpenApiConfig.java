package com.concursospublicosbr.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

@Configuration
public class OpenApiConfig {

    private final MessageSource messageSource;

    public OpenApiConfig(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Bean
    public OpenAPI customOpenAPI() {
        Components components = new Components()
                .addSecuritySchemes("bearerAuth",
                        new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                );

        return new OpenAPI()
                .components(components)
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .info(new Info()
                        .title(messageSource.getMessage("openapi.title", null, LocaleContextHolder.getLocale()))
                        .description(messageSource.getMessage("openapi.description", null, LocaleContextHolder.getLocale()))
                        .version(messageSource.getMessage("openapi.version", null, LocaleContextHolder.getLocale()))
                        .license(new License()
                                .name(messageSource.getMessage("openapi.license.name", null, LocaleContextHolder.getLocale()))
                                .url(messageSource.getMessage("openapi.license.url", null, LocaleContextHolder.getLocale())))
                        .contact(new Contact()
                                .name(messageSource.getMessage("openapi.contact.name", null, LocaleContextHolder.getLocale()))
                                .email(messageSource.getMessage("openapi.contact.email", null, LocaleContextHolder.getLocale()))))
                .externalDocs(new ExternalDocumentation()
                        .description(messageSource.getMessage("openapi.externalDocs.description", null, LocaleContextHolder.getLocale()))
                        .url("https://springdoc.org/"));
    }
}


