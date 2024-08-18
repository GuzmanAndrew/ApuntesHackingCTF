package com.ctf.vuelos_servicio.services.impl;

import com.ctf.vuelos_servicio.entities.Vuelo;
import com.ctf.vuelos_servicio.repositories.VueloRepository;
import com.ctf.vuelos_servicio.services.VueloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VueloServiceImpl implements VueloService {

    @Autowired
    private VueloRepository vueloRepository;

    @Override
    public List<Vuelo> recuperarVuelosDisponibles(int plazas) {
        return vueloRepository.findAll().stream()
                .filter(t -> t.getPlazas() >= plazas)
                .collect(Collectors.toList());
    }

    @Override
    public void actualizarPlazas(int id, int plazas) {
        Vuelo vuelo = vueloRepository.findById(id).orElse(null);
        if (vuelo != null) {
            vuelo.setPlazas(vuelo.getPlazas() - plazas);
            vueloRepository.save(vuelo);
        }
    }

    @Override
    public Vuelo obtenerVueloPorId(Integer id) {
        return vueloRepository.findById(id).orElse(null);
    }
}
