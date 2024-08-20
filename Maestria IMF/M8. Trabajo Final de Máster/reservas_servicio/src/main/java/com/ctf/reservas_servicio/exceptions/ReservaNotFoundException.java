package com.ctf.reservas_servicio.exceptions;

public class ReservaNotFoundException extends RuntimeException {

   public ReservaNotFoundException(String message) {
      super(message);
   }

   public ReservaNotFoundException(String message, Throwable cause) {
      super(message, cause);
   }
}
