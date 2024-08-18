package com.ctf.hotel_servicio.services;

import com.ctf.hotel_servicio.entities.Hotel;

import java.util.List;

public interface HotelService {

    List<Hotel> devolverHotelesDisponibles ();

    Hotel obtenerHotelPorId (Integer id);
}
