package com.ctf.usuarios_servicio.services.impl;

import com.ctf.usuarios_servicio.entities.Usuario;
import com.ctf.usuarios_servicio.repositories.UsuarioRepository;
import com.ctf.usuarios_servicio.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

   @Autowired
   private UsuarioRepository usuarioRepository;

   @Override
   public List<Usuario> obtenerUsuarios() {
      return usuarioRepository.findAll();
   }

   @Override
   public Usuario obtenerUsuarioPorId(Long id) {
      return usuarioRepository.findById(id).orElse(null);
   }

   @Override
   public Usuario obtenerUsuarioPorNombre(String nombre) {
      return usuarioRepository.findByUsuario(nombre).orElse(null);
   }

   @Override
   public Usuario guardarUsuario(Usuario usuario) {
      return usuarioRepository.save(usuario);
   }

   @Override
   public Usuario actualizarUsuario(Usuario usuario) {
      return usuarioRepository.save(usuario);
   }

   @Override
   public void eliminarUsuario(Long id) {
      usuarioRepository.deleteById(id);
   }
}
