package ar.edu.unlam.tallerweb1.modelo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Horario other = (Horario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
