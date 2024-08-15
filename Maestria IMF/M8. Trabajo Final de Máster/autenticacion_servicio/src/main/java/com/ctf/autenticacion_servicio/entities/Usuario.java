package com.ctf.autenticacion_servicio.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuarios")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id_usuarios")
   private Long idUsuarios;

   @Column(name = "usuario")
   private String usuario;

   @Column(name = "contraseña")
   private String contrasena;

   @Column(name = "nombres")
   private String nombres;

   @Column(name = "apellidos")
   private String apellidos;

   @Column(name = "correo")
   private String correo;

   @Column(name = "telefono")
   private String telefono;

   @Column(name = "is_admin")
   private Boolean isAdmin;
}
