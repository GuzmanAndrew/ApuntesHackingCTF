package com.ctf.reservas_servicio.controllers;

import com.ctf.reservas_servicio.dto.ReservaDTO;
import com.ctf.reservas_servicio.entities.Reserva;
import com.ctf.reservas_servicio.services.impl.ReservaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private ReservaServiceImpl reservaService;

    @PostMapping("/save/{personas}")
    public ResponseEntity<String> generarReserva(@RequestBody Reserva reserva,
                                                 @PathVariable("personas") int personas,
                                                 @RequestHeader("Authorization") String token) {
        reservaService.realizarReserva(reserva, personas, token);
        return new ResponseEntity<>("Reserva exitosa", HttpStatus.OK);
    }

    @GetMapping("/all/by-user/{usuario}")
    public ResponseEntity<List<ReservaDTO>> getReservas(@PathVariable("usuario") String usuario,
                                                        @RequestHeader("Authorization") String token) {

        return new ResponseEntity<>(reservaService.getReservas(usuario, token), HttpStatus.OK);
    }
}
