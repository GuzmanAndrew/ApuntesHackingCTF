package com.ctf.usuarios_servicio.services;

import com.ctf.usuarios_servicio.entities.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

   List<Usuario> obtenerUsuarios();

   Usuario obtenerUsuarioPorId(Long id);

   Usuario obtenerUsuarioPorNombre(String nombre);

   Usuario guardarUsuario(Usuario usuario);

   Usuario actualizarUsuario(Long id, Usuario usuario);

   void eliminarUsuario(Long id);
}
