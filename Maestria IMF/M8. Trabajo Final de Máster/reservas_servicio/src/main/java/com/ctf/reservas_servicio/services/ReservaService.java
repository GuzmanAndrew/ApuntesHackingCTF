package com.ctf.reservas_servicio.services;

import com.ctf.reservas_servicio.dto.ReservaDTO;
import com.ctf.reservas_servicio.entities.Reserva;
import com.ctf.reservas_servicio.entities.Usuario;

import java.util.List;

public interface ReservaService {

    void realizarReserva(Reserva reserva, int totalPersonas, String token);

    List<ReservaDTO> getReservas(String usuario, String token);

    Usuario obtenerUsuarioPorNombre(String nombreUsuario, String token);

    void cancelarReserva(Integer idReserva);

    String obtenerInformacionAdicional(String url);
}
