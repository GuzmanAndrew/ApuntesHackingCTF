package com.ctf.hotel_servicio.repositories;

import com.ctf.hotel_servicio.entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel,Integer> {
}
