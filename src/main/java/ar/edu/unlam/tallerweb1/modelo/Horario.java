package ar.edu.unlam.tallerweb1.modelo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Horario {
	
	@Id
	@GeneratedValue
	private Long id;
	private Date horaInicio;
	private Date horaFin;
	private Boolean permitirSeleccionHorario;
	private Boolean macheado;
	
	@ManyToOne()
	private Equipo equipo;

	@ManyToOne()
	private Fecha fecha;

	public Horario(){
		this.permitirSeleccionHorario=true;
		this.macheado=false;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Date horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Date getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(Date horaFin) {
		this.horaFin = horaFin;
	}

	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	public Fecha getFecha() {
		return fecha;
	}

	public void setFecha(Fecha fecha) {
		this.fecha = fecha;
	}
	public Boolean getPermitirSeleccionHorario() {
		return permitirSeleccionHorario;
	}
	public void setPermitirSeleccionHorario(Boolean permitirSeleccionHorario) {
		this.permitirSeleccionHorario = permitirSeleccionHorario;
	}
	public Boolean getMacheado() {
		return macheado;
	}
	public void setMacheado(Boolean macheado) {
		this.macheado = macheado;
	}

}
