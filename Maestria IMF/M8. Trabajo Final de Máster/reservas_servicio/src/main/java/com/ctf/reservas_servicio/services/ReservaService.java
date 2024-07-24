package com.ctf.reservas_servicio.services;

import com.ctf.reservas_servicio.models.Reserva;

import java.util.List;

public interface ReservaService {

    void realizarReserva(Reserva reserva, int totalPersonas);

    List<Reserva> getReservas();
}
