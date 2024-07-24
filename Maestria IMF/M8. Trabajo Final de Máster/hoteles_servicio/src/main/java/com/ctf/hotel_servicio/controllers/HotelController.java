package com.ctf.hotel_servicio.controllers;

import com.ctf.hotel_servicio.models.Hotel;
import com.ctf.hotel_servicio.services.HotelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
