package com.ctf.reservas_servicio.repositories;

import com.ctf.reservas_servicio.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

   Usuario findByUsuario(String usuario);
}
