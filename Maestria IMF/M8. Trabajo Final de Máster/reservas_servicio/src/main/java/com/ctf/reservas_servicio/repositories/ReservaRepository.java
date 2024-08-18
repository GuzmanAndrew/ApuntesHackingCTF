package com.ctf.reservas_servicio.repositories;

import com.ctf.reservas_servicio.entities.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva,Integer> {

   List<Reserva> findByUsuario_Usuario(String usuario);
}
