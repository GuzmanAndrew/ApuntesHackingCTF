package com.ctf.hotel_servicio.controllers;

import com.ctf.hotel_servicio.entities.Hotel;
import com.ctf.hotel_servicio.services.impl.HotelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/hoteles")
public class HotelController {

    @Autowired
    private HotelServiceImpl service;

    @GetMapping("/all")
    private List<Hotel> devolverHoteles() {
        return service.devolverHotelesDisponibles();
    }

    @GetMapping("/by-id/{id}")
    private ResponseEntity<Hotel> obtenerUsuarioPorId(@PathVariable("id") Integer id){
        return new ResponseEntity<>(service.obtenerHotelPorId(id), HttpStatus.OK);
    }
}
