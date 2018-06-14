package ar.edu.unlam.tallerweb1.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Equipo {

	@Id
	@GeneratedValue
	private Long id;
	private String nombreEquipo;
	//private List<String> jugadores = new ArrayList<String>();
	@ManyToOne()
	private Usuario usuario;
	@ManyToOne()
	private Torneo torneo;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombreEquipo() {
		return nombreEquipo;
	}
	public void setNombreEquipo(String nombreEquipo) {
		this.nombreEquipo = nombreEquipo;
	}
	/*public List<String> getJugadores() {
		return jugadores;
	}
	public void setJugadores(List<String> jugadores) {
		this.jugadores = jugadores;
	}*/
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Torneo getTorneo() {
		return torneo;
	}
	public void setTorneo(Torneo torneo) {
		this.torneo = torneo;
	}

}
