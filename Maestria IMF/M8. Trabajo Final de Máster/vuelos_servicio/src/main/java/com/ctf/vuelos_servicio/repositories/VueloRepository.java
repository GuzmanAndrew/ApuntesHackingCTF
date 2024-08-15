package com.ctf.vuelos_servicio.repositories;

import com.ctf.vuelos_servicio.entities.Vuelo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VueloRepository extends JpaRepository<Vuelo,Integer> {
}
