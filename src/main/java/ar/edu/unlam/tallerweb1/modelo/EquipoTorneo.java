package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class EquipoTorneo {//CLASE FUERA DE USO
	@Id
	@GeneratedValue
	private Integer id;
	
	@ManyToOne
	private Equipo equipo;
	
	@ManyToOne
	private Torneo torneo;
}
