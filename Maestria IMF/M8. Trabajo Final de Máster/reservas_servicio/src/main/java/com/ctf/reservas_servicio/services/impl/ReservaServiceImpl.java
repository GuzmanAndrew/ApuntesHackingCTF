package com.ctf.reservas_servicio.services.impl;

import com.ctf.reservas_servicio.entities.Reserva;
import com.ctf.reservas_servicio.entities.Usuario;
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

import java.util.List;

@Service
public class ReservaServiceImpl implements ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RestTemplate template;

    private String urlVuelos = "http://localhost:8001/vuelos";

    private String urlUsuarios = "http://localhost:8003/usuarios/by-username";

    @Override
    public void realizarReserva(Reserva reserva, int totalPersonas, String token) {

        Usuario usuarioDto = obtenerUsuarioPorNombre(reserva.getUsuario().getUsuario(), token);

        if (usuarioDto == null) {
            throw new UserNotFoundException("Usuario no encontrado");
        }

        Usuario usuario = usuarioRepository.findByUsuario(usuarioDto.getUsuario())
                .orElseThrow(() -> new UserNotFoundException("Usuario no encontrado en la base de datos"));

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
    public List<Reserva> getReservas(String usuario) {
        return reservaRepository.findByUsuario_Usuario(usuario);
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
}
