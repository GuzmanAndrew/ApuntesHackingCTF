package com.ctf.reservas_servicio.services.impl;

import com.ctf.reservas_servicio.dto.HotelDTO;
import com.ctf.reservas_servicio.dto.ReservaDTO;
import com.ctf.reservas_servicio.dto.VueloDTO;
import com.ctf.reservas_servicio.entities.Reserva;
import com.ctf.reservas_servicio.entities.Usuario;
import com.ctf.reservas_servicio.exceptions.ReservaNotFoundException;
import com.ctf.reservas_servicio.exceptions.UserNotFoundException;
import com.ctf.reservas_servicio.repositories.ReservaRepository;
import com.ctf.reservas_servicio.repositories.UsuarioRepository;
import com.ctf.reservas_servicio.services.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReservaServiceImpl implements ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private UsuarioRepository userRepository;

    @Autowired
    private RestTemplate template;

    private String urlVuelos = "http://vuelos-servicio:8001/vuelos";

    private String urlUsuarios = "http://usuarios-servicio:8003/usuarios/by-username";

    @Override
    public void realizarReserva(Reserva reserva, int totalPersonas, String token) {

        Usuario usuarioDto = obtenerUsuarioPorNombre(reserva.getUsuario().getUsuario(), token);

        if (usuarioDto == null) {
            throw new UserNotFoundException("Usuario no encontrado");
        }

        Usuario usuario = userRepository.findByUsuario(usuarioDto.getUsuario());

        reserva.setUsuario(usuario);
        reservaRepository.save(reserva);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        template.exchange(
                urlVuelos + "/update/{p1}/{p2}",
                HttpMethod.PUT,
                entity,
                Void.class,
                reserva.getVuelo(),
                totalPersonas
        );
    }

    @Override
    public List<ReservaDTO> getReservas(String usuario, String token) {
        List<Reserva> reservas = reservaRepository.findByUsuario_Usuario(usuario);
        List<ReservaDTO> reservasDTO = new ArrayList<>();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        for (Reserva reserva : reservas) {
            ResponseEntity<HotelDTO> hotelResponse = template.exchange(
                    "http://hoteles-servicio:8000/hoteles/by-id/" + reserva.getHotel(),
                    HttpMethod.GET,
                    entity,
                    HotelDTO.class);

            String hotelNombre = hotelResponse.getBody().getNombre();

            ResponseEntity<VueloDTO> vueloResponse = template.exchange(
                    "http://vuelos-servicio:8001/vuelos/by-id/" + reserva.getVuelo(),
                    HttpMethod.GET,
                    entity,
                    VueloDTO.class);

            String vueloCompania = vueloResponse.getBody().getCompany();

            ReservaDTO reservaDTO = new ReservaDTO(
                    reserva.getIdreserva(),
                    hotelNombre,
                    vueloCompania,
                    reserva.getUsuario().getUsuario()
            );

            reservasDTO.add(reservaDTO);
        }

        return reservasDTO;
    }

    @Override
    public Usuario obtenerUsuarioPorNombre(String nombreUsuario, String token) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<Usuario> response = template.exchange(
                    urlUsuarios + "/" + nombreUsuario,
                    HttpMethod.GET,
                    entity,
                    Usuario.class
            );
            return response.getBody();
        } catch (Exception e) {
            throw new UserNotFoundException("Error al obtener el usuario: " + nombreUsuario, e);
        }
    }

    @Override
    public void cancelarReserva(Integer idReserva) {
        Optional<Reserva> reservaOpt = reservaRepository.findById(idReserva);
        if (!reservaOpt.isPresent()) {
            throw new ReservaNotFoundException("Reserva no encontrada");
        }

        reservaRepository.deleteById(idReserva);
    }

    @Override
    public String obtenerInformacionAdicional(String url) {
        return template.getForObject(url, String.class);
    }
}
