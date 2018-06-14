package ar.edu.unlam.tallerweb1.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Torneo {

	@Id
	@GeneratedValue
	private Long id;
	private String nombreTorneo;
	private String descripcionTorneo;
	
	@ManyToMany()
	private List<Cancha> listaDeCanchas = new ArrayList<Cancha>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombreTorneo() {
		return nombreTorneo;
	}

	public void setNombreTorneo(String nombreTorneo) {
		this.nombreTorneo = nombreTorneo;
	}

	public String getDescripcionTorneo() {
		return descripcionTorneo;
	}

	public void setDescripcionTorneo(String descripcionTorneo) {
		this.descripcionTorneo = descripcionTorneo;
	}

	public List<Cancha> getListaDeCanchas() {
		return listaDeCanchas;
	}

	public void setListaDeCanchas(List<Cancha> listaDeCanchas) {
		this.listaDeCanchas = listaDeCanchas;
	}
}
