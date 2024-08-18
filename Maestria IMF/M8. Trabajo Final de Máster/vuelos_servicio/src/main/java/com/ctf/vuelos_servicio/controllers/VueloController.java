package com.ctf.vuelos_servicio.controllers;

import com.ctf.vuelos_servicio.entities.Vuelo;
import com.ctf.vuelos_servicio.services.impl.VueloServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/vuelos")
public class VueloController {

    @Autowired
    private VueloServiceImpl vueloServiceImpl;

    @GetMapping("/all/{plazas}")
    public List<Vuelo> devolverVuelos(@PathVariable("plazas") int plazas) {
        return vueloServiceImpl.recuperarVuelosDisponibles(plazas);
    }

    @GetMapping("/by-id/{id}")
    private ResponseEntity<Vuelo> obtenerUsuarioPorId(@PathVariable("id") Integer id){
        return new ResponseEntity<>(vueloServiceImpl.obtenerVueloPorId(id), HttpStatus.OK);
    }

    @PutMapping("/update/{idvuelo}/{plazas}")
    public ResponseEntity<String> modificarVuelo (@PathVariable("idvuelo") int id,
                                          @PathVariable("plazas") int plazas) {
        vueloServiceImpl.actualizarPlazas(id, plazas);
        return new ResponseEntity<>("Actualización de plazas con exito", HttpStatus.OK);
    }
}
