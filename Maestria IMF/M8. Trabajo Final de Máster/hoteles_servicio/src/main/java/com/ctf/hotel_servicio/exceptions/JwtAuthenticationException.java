package com.ctf.hotel_servicio.exceptions;

import org.springframework.http.HttpStatus;

public class JwtAuthenticationException extends RuntimeException {

   private final HttpStatus httpStatus;

   public JwtAuthenticationException(String message, HttpStatus status) {
      super(message);
      this.httpStatus = status;
   }

   public HttpStatus getHttpStatus() {
      return httpStatus;
   }
}