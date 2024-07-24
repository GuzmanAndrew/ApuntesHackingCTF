package com.ctf.reservas_servicio.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the reservas database table.
 * 
 */
@Data
@Entity
@Table(name="reservas")
@AllArgsConstructor
@NoArgsConstructor
public class Reserva {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "idreserva")
	private int idreserva;

	@Column(name = "dni")
	private String dni;

	@Column(name = "hotel")
	private int hotel;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "vuelo")
	private int vuelo;

}