package ar.edu.unlam.tallerweb1.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Equipo {

	@Id
	@GeneratedValue
	private Long id;
	private String nombreEquipo;
	@ManyToOne()
	private Usuario usuario;
	@ManyToMany(cascade = { 
			
		        CascadeType.ALL
		    
	    }, fetch = FetchType.EAGER)
	    @JoinTable(name = "equipo_torneo",
	        joinColumns = @JoinColumn(name = "equipo_id"),
	        inverseJoinColumns = @JoinColumn(name = "torneo_id")
	    )
	private List<Torneo> torneos;
	
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
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public List<Torneo> getTorneos() {
		return torneos;
	}
	public void setTorneos(List<Torneo> torneos) {
		this.torneos = torneos;
	}

}
