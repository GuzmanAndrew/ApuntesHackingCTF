package com.ctf.usuarios_servicio.repositories;

import com.ctf.usuarios_servicio.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

   Optional<Usuario> findByUsuario(String username);

}
