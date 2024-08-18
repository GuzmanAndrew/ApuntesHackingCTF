package com.ctf.hotel_servicio.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="hoteles")
@AllArgsConstructor
@NoArgsConstructor
public class Hotel {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_hotel")
	private int idHotel;

	@Column(name = "categoria")
	private int categoria;

	@Column(name = "disponible")
	private byte disponible;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "precio")
	private double precio;

}