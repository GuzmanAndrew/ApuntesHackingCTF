package com.ctf.vuelos_servicio.services;

import com.ctf.vuelos_servicio.entities.Vuelo;

import java.util.List;
import java.util.Optional;

public interface VueloService {

    List<Vuelo> recuperarVuelosDisponibles(int plazas);

    void actualizarPlazas(int id, int plazas);

    Vuelo obtenerVueloPorId(Integer id);
}
