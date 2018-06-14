package ar.edu.unlam.tallerweb1.modelo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Partido {
	
	@Id
	@GeneratedValue
	private Long id;
	private Date horario;
	@OneToOne
	private Equipo equipo1 = new Equipo();
	@OneToOne
	private Equipo equipo2 = new Equipo();
	private Boolean finalizado;
	private Long golesEquipo1;
	private Long golesEquipo2;
	
	@ManyToOne()
	private Fecha fecha;
	@ManyToOne()
	private Cancha cancha;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getHorario() {
		return horario;
	}
	public void setHorario(Date horario) {
		this.horario = horario;
	}
	public Equipo getEquipo1() {
		return equipo1;
	}
	public void setEquipo1(Equipo equipo1) {
		this.equipo1 = equipo1;
	}
	public Equipo getEquipo2() {
		return equipo2;
	}
	public void setEquipo2(Equipo equipo2) {
		this.equipo2 = equipo2;
	}
	public Boolean getFinalizado() {
		return finalizado;
	}
	public void setFinalizado(Boolean finalizado) {
		this.finalizado = finalizado;
	}
	public Long getGolesEquipo1() {
		return golesEquipo1;
	}
	public void setGolesEquipo1(Long golesEquipo1) {
		this.golesEquipo1 = golesEquipo1;
	}
	public Long getGolesEquipo2() {
		return golesEquipo2;
	}
	public void setGolesEquipo2(Long golesEquipo2) {
		this.golesEquipo2 = golesEquipo2;
	}
	public Fecha getFecha() {
		return fecha;
	}
	public void setFecha(Fecha fecha) {
		this.fecha = fecha;
	}
	public Cancha getCancha() {
		return cancha;
	}
	public void setCancha(Cancha cancha) {
		this.cancha = cancha;
	}
	
}
