package com.ctf.autenticacion_servicio.controller;

import com.ctf.autenticacion_servicio.entities.AuthenticationRequest;
import com.ctf.autenticacion_servicio.entities.AuthenticationResponse;
import com.ctf.autenticacion_servicio.jwt.JwtUtil;
import com.ctf.autenticacion_servicio.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

   @Autowired
   private AuthenticationManager authenticationManager;

   @Autowired
   private CustomUserDetailsService detalleUsuarioService;

   @Autowired
   private JwtUtil jwtUtil;

   @PostMapping("/authenticate")
   public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

      try {
         authenticationManager.authenticate(
                 new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
         );
      } catch (BadCredentialsException e) {
         throw new Exception("Incorrect username or password", e);
      }

      final UserDetails userDetails = detalleUsuarioService
              .loadUserByUsername(authenticationRequest.getUsername());

      final String jwt = jwtUtil.generateToken(userDetails);

      return ResponseEntity.ok(new AuthenticationResponse(jwt));
   }
}
