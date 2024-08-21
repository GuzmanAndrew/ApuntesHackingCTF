package com.ctf.autenticacion_servicio.config;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

   @Bean
   public GroupedOpenApi publicApi() {
      return GroupedOpenApi.builder()
              .group("authentication-service")
              .pathsToMatch("/authenticate/**")
              .build();
   }
}
