package com.ctf.vuelos_servicio.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="vuelos")
@AllArgsConstructor
@NoArgsConstructor
public class Vuelo  {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "idvuelo")
	private int idvuelo;

	@Column(name = "company")
	private String company;

	@Column(name = "fecha")
	private String fecha;

	@Column(name = "plazas")
	private int plazas;

	@Column(name = "precio")
	private double precio;

}