package com.ctf.autenticacion_servicio.entities;

import lombok.Data;

@Data
public class AuthenticationRequest {

   private String username;
   private String password;
}
