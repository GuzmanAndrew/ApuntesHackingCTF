package com.ctf.vuelos_servicio.jwt;

import com.ctf.vuelos_servicio.exceptions.JwtAuthenticationException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
   @Override
   protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                   FilterChain filterChain) throws ServletException, IOException {

      String token = resolveToken(request);

      if (token != null) {
         try {
            Claims claims = Jwts.parser()
                    .setSigningKey(getSigningKey()).build()
                    .parseClaimsJws(token)
                    .getBody();

            if (claims.getExpiration().before(new Date())) {
               throw new JwtAuthenticationException("Token has expired", HttpStatus.UNAUTHORIZED);
            }

            Authentication authentication = getAuthentication(claims, request);
            SecurityContextHolder.getContext().setAuthentication(authentication);

         } catch (JwtAuthenticationException e) {
            response.sendError(e.getHttpStatus().value(), e.getMessage());
            return;
         } catch (UnsupportedJwtException e) {
            throw new JwtAuthenticationException("Unsupported JWT token", HttpStatus.UNAUTHORIZED);
         } catch (MalformedJwtException e) {
            throw new JwtAuthenticationException("Malformed JWT token", HttpStatus.UNAUTHORIZED);
         } catch (SignatureException e) {
            throw new JwtAuthenticationException("Invalid JWT signature", HttpStatus.UNAUTHORIZED);
         } catch (IllegalArgumentException e) {
            throw new JwtAuthenticationException("JWT claims string is empty", HttpStatus.UNAUTHORIZED);
         }
      }

      filterChain.doFilter(request, response);
   }

   private String resolveToken(HttpServletRequest request) {
      String bearerToken = request.getHeader("Authorization");
      if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
         return bearerToken.substring(7);
      }
      return null;
   }

   private Key getSigningKey() {
      String SECRET_KEY = "mySecretKeyForJwtTokens12345678901234567890123456789012";
      byte[] keyBytes = SECRET_KEY.getBytes();
      return Keys.hmacShaKeyFor(keyBytes);
   }

   private Authentication getAuthentication(Claims claims, HttpServletRequest request) {
      String username = claims.getSubject();

      List<Map<String, String>> authoritiesMap = claims.get("authorities", List.class);
      List<GrantedAuthority> authorities = authoritiesMap.stream()
              .map(roleMap -> new SimpleGrantedAuthority(roleMap.get("authority")))
              .collect(Collectors.toList());

      UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
              username, null, authorities
      );

      authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

      return authentication;
   }
}
