package com.ctf.autenticacion_servicio.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {

   private Key getSigningKey() {
      String SECRET_KEY = "mySecretKeyForJwtTokens12345678901234567890123456789012";
      byte[] keyBytes = SECRET_KEY.getBytes();
      return Keys.hmacShaKeyFor(keyBytes);
   }

   public String generateToken(UserDetails userDetails) {
      Map<String, Object> claims = new HashMap<>();
      claims.put("username", userDetails.getUsername());
      claims.put("authorities", userDetails.getAuthorities());
      return createToken(claims, "api vulnerabilities");
   }

   private String createToken(Map<String, Object> claims, String subject) {
      return Jwts.builder()
              .setClaims(claims)
              .setSubject(subject)
              .setIssuedAt(new Date(System.currentTimeMillis()))
              .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
              .signWith(getSigningKey())
              .compact();
   }

}
