package com.ctf.reservas_servicio.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservaDTO {

   private int idreserva;
   private String hotel;
   private String vuelo;
   private String usuario;
}
