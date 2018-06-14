package ar.edu.unlam.tallerweb1.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Cancha {

	@Id
	@GeneratedValue
	private Long id;
	private String nombreCancha;
	private String ubicacion;
	@ManyToMany()
	private List<Torneo> listaDeTorneos = new ArrayList<Torneo>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombreCancha() {
		return nombreCancha;
	}

	public void setNombreCancha(String nombreCancha) {
		this.nombreCancha = nombreCancha;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public List<Torneo> getListaDeTorneos() {
		return listaDeTorneos;
	}

	public void setListaDeTorneos(List<Torneo> listaDeTorneos) {
		this.listaDeTorneos = listaDeTorneos;
	}

}
