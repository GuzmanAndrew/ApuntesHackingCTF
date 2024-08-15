package com.ctf.usuarios_servicio.controllers;

import com.ctf.usuarios_servicio.entities.ErrorResponse;
import com.ctf.usuarios_servicio.exceptions.JwtAuthenticationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

   @ExceptionHandler(JwtAuthenticationException.class)
   public ResponseEntity<ErrorResponse> handleJwtAuthenticationException(JwtAuthenticationException ex) {
      return new ResponseEntity<>(new ErrorResponse(ex.getHttpStatus().value(), ex.getMessage()),
              ex.getHttpStatus());
   }

   @ExceptionHandler(Exception.class)
   public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex) {
      return new ResponseEntity<>(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
              "An unexpected error occurred"), HttpStatus.INTERNAL_SERVER_ERROR);
   }
}
