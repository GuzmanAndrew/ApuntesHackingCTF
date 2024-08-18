package com.ctf.vuelos_servicio.config;

import com.ctf.vuelos_servicio.jwt.JwtRequestFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

   private final JwtRequestFilter jwtRequestFilter;

   public SecurityConfig(JwtRequestFilter jwtRequestFilter) {
      this.jwtRequestFilter = jwtRequestFilter;
   }

   @Bean
   public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
      http
              .csrf(csrf -> csrf.disable())
              .authorizeRequests(auth -> auth
                      .requestMatchers("/vuelos/**").authenticated()
                      .anyRequest().permitAll()
              )
              .sessionManagement(session -> session
                      .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
              )
              .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

      return http.build();
   }

   @Bean
   public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
      return authenticationConfiguration.getAuthenticationManager();
   }
}
