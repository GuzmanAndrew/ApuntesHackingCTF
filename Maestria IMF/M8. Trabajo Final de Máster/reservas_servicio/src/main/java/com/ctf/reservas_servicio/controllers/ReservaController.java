package com.ctf.reservas_servicio.controllers;

import com.ctf.reservas_servicio.models.Reserva;
import com.ctf.reservas_servicio.services.ReservaServiceImpl;
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
    public ResponseEntity<String> generarReserva(@RequestBody Reserva reserva, @PathVariable("personas") int personas) {
        reservaService.realizarReserva(reserva, personas);
        return new ResponseEntity<>("Reserva exitosa", HttpStatus.OK);
    }

    @GetMapping("/all")
    public List<Reserva> getReservas() {
        return reservaService.getReservas();
    }
}
