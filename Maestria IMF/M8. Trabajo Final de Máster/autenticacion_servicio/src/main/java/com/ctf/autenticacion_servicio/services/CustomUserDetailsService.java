package com.ctf.autenticacion_servicio.services;

import com.ctf.autenticacion_servicio.entities.Usuario;
import com.ctf.autenticacion_servicio.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

   @Autowired
   private UsuarioRepository usuarioRepository;

   @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      Usuario usuario = usuarioRepository.findByUsuario(username)
              .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

      String password = "{noop}" + usuario.getContrasena();

      String authority = usuario.getIsAdmin() ? "ROLE_ADMIN" : "ROLE_USER";
      List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(authority));

      return new User(usuario.getUsuario(), password, authorities);
   }
}
