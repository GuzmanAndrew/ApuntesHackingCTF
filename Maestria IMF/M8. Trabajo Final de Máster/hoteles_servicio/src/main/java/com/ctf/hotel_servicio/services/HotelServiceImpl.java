package com.ctf.hotel_servicio.services;

import com.ctf.hotel_servicio.models.Hotel;
import com.ctf.hotel_servicio.repositories.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public List<Hotel> devolverHotelesDisponibles() {
        List<Hotel> hoteles = hotelRepository.findAll();
        return hoteles.stream()
                .filter(t -> t.getDisponible() == 1)
                .collect(Collectors.toList());
    }
}
