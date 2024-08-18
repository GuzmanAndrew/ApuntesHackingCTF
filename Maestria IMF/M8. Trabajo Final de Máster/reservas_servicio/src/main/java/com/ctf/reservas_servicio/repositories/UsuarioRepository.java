package com.ctf.reservas_servicio.repositories;

import com.ctf.reservas_servicio.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

   Optional<Usuario> findByUsuario(String username);
}
