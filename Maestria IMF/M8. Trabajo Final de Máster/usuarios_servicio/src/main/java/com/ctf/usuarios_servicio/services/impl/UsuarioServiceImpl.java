package com.ctf.usuarios_servicio.services.impl;

import com.ctf.usuarios_servicio.entities.Usuario;
import com.ctf.usuarios_servicio.repositories.UsuarioRepository;
import com.ctf.usuarios_servicio.services.UsuarioService;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

   @Autowired
   private UsuarioRepository usuarioRepository;

   @PersistenceContext
   private EntityManager em;

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
      String sql =  "SELECT * FROM usuarios WHERE usuario = '" + nombre + "'";
      System.out.println("QUERY: " + sql);
      Query query = em.createNativeQuery(sql, Usuario.class);
      List<Usuario> resultados = query.getResultList();

      if (resultados.size() == 1) {
         return resultados.get(0);
      } else if (resultados.isEmpty()) {
         throw new EntityNotFoundException("No se encontró ningún usuario con ese nombre");
      } else {
         throw new NonUniqueResultException("La consulta devolvió más de un resultado");
      }
   }

   @Override
   public Usuario guardarUsuario(Usuario usuario) {
      if (usuario.getIsAdmin() == null) {
         usuario.setIsAdmin(false);
      }
      return usuarioRepository.save(usuario);
   }

   @Override
   public Usuario actualizarUsuario(Long id, Usuario usuario) {

      Usuario usuarioExistente = usuarioRepository.findById(id)
              .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));

      usuarioExistente.setUsuario(usuario.getUsuario());
      usuarioExistente.setContrasena(usuario.getContrasena());
      usuarioExistente.setNombres(usuario.getNombres());
      usuarioExistente.setApellidos(usuario.getApellidos());
      usuarioExistente.setCorreo(usuario.getCorreo());
      usuarioExistente.setTelefono(usuario.getTelefono());
      usuarioExistente.setIsAdmin(usuario.getIsAdmin());

      return usuarioRepository.save(usuarioExistente);
   }

   @Override
   public void eliminarUsuario(Long id) {
      usuarioRepository.deleteById(id);
   }
}
