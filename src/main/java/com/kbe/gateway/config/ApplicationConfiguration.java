package com.kbe.gateway.config;

import com.kbe.gateway.controller.ControllerUtil;
import com.kbe.gateway.controller.ControllerUtilImpl;
import org.keycloak.adapters.KeycloakConfigResolver;
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public KeycloakConfigResolver KeycloakConfigResolver() {
        return new KeycloakSpringBootConfigResolver();
    }

    @Bean
    public ControllerUtil getControllerUtil() { return new ControllerUtilImpl(); }
}
