package com.ctf.reservas_servicio.services;

import com.ctf.reservas_servicio.models.Reserva;
import com.ctf.reservas_servicio.repositories.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ReservaServiceImpl implements ReservaService{

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private RestTemplate template;

    private String url = "http://localhost:8001/vuelos";

    @Override
    public void realizarReserva(Reserva reserva, int totalPersonas) {
        reservaRepository.save(reserva);
        template.put(url + "/update/{p1}/{p2}", null, reserva.getVuelo(), totalPersonas);
    }

    @Override
    public List<Reserva> getReservas() {
        return reservaRepository.findAll();
    }
}
