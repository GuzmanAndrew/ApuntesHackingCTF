package com.ctf.usuarios_servicio.handler;

import jakarta.persistence.NonUniqueResultException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

   @ExceptionHandler(NonUniqueResultException.class)
   public ResponseEntity<Map<String, Object>> handleNonUniqueResultException(NonUniqueResultException ex) {
      Map<String, Object> response = new HashMap<>();
      response.put("timestamp", new Date());
      response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
      response.put("error", "NonUniqueResultException");
      response.put("message", ex.getMessage());
      response.put("details", "The query returned more than one result");
      return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
   }

   @ExceptionHandler(Exception.class)
   public ResponseEntity<Map<String, Object>> handleAllExceptions(Exception ex) {
      Map<String, Object> response = new HashMap<>();
      response.put("timestamp", new Date());
      response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
      response.put("error", "Internal Server Error");
      response.put("message", ex.getMessage());
      response.put("details", ex.getClass().getName());
      return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
   }
}
