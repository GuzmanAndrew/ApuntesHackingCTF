package com.ctf.usuarios_servicio.controllers;

import com.ctf.usuarios_servicio.entities.Usuario;
import com.ctf.usuarios_servicio.services.impl.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins="*")
public class UsuarioController {

   @Autowired
   private UsuarioServiceImpl usuarioServiceImpl;

   @GetMapping("/all")
   private ResponseEntity<?> obtenerUsuarios(){
      return new ResponseEntity<>(usuarioServiceImpl.obtenerUsuarios(), HttpStatus.OK);
   }

   @GetMapping("/by-id/{id}")
   private ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable("id") Long id){
      return new ResponseEntity<>(usuarioServiceImpl.obtenerUsuarioPorId(id), HttpStatus.OK);
   }

   @GetMapping("/by-username/{username}")
   private ResponseEntity<Usuario> obtenerUsuarioPorNombreDeUsuario(@PathVariable("username") String username){
      return new ResponseEntity<>(usuarioServiceImpl.obtenerUsuarioPorNombre(username), HttpStatus.OK);
   }

   @PostMapping("/create")
   private ResponseEntity<Usuario> insertarUsuario(@RequestBody Usuario usuario){
      return new ResponseEntity<>(usuarioServiceImpl.guardarUsuario(usuario), HttpStatus.CREATED);
   }

   @PutMapping("/update/{id}")
   private ResponseEntity<Usuario> actualizarUsuario(@PathVariable("id") Long id,
                                                     @RequestBody Usuario usuario){

      return new ResponseEntity<>(usuarioServiceImpl.actualizarUsuario(id, usuario), HttpStatus.OK);
   }

   @PreAuthorize("hasRole('ADMIN')")
   @DeleteMapping("/delete/{id}")
   private ResponseEntity<String> eliminarUsuario(@PathVariable("id") Long id){
      usuarioServiceImpl.eliminarUsuario(id);
      return new ResponseEntity<>("¡Usuario eliminado con exito!", HttpStatus.OK);
   }
}
