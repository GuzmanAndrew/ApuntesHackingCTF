package com.ctf.reservas_servicio.repositories;

import com.ctf.reservas_servicio.models.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservaRepository extends JpaRepository<Reserva,Integer> {
}
