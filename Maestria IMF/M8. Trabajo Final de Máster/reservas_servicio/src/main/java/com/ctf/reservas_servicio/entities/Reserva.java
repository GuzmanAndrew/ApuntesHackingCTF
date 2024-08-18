package com.ctf.reservas_servicio.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

	@ManyToOne
	@JoinColumn(name = "usuario_id", nullable = false)
	private Usuario usuario;

	@Column(name = "hotel")
	private int hotel;

	@Column(name = "vuelo")
	private int vuelo;

}