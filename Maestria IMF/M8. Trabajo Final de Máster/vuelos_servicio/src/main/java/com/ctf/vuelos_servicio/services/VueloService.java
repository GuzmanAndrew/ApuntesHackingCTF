package com.ctf.vuelos_servicio.services;

import com.ctf.vuelos_servicio.models.Vuelo;

import java.util.List;

public interface VueloService {

    List<Vuelo> recuperarVuelosDisponibles(int plazas);

    void actualizarPlazas(int id, int plazas);
}
