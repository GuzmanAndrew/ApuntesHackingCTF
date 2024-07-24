package com.ctf.hotel_servicio.services;

import com.ctf.hotel_servicio.models.Hotel;

import java.util.List;

public interface HotelService {

    List<Hotel> devolverHotelesDisponibles ();
}
