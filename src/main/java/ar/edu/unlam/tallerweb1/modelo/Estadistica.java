package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


public class Estadistica implements Comparable<Estadistica> {

	private Long golesAFavor;
	private Long golesEnContra;
	private Long partidosGanados;
	private Long partidosEmpatados;
	private Long partidosPerdidos;
	private Long puntos;
	private Equipo equipo;
	
	public Estadistica(){
		this.golesAFavor=0L;
		this.golesEnContra=0L;
		this.partidosGanados=0L;
		this.partidosEmpatados=0L;
		this.partidosPerdidos=0L;
		this.puntos=0L;
	}
	
	public Long getPuntos() {
		return puntos;
	}

	public void setPuntos(Long puntos) {
		this.puntos = puntos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((equipo == null) ? 0 : equipo.hashCode());
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
		Estadistica other = (Estadistica) obj;
		if (equipo == null) {
			if (other.equipo != null)
				return false;
		} else if (!equipo.equals(other.equipo))
			return false;
		return true;
	}
	public Long getGolesAFavor() {
		return golesAFavor;
	}
	public void setGolesAFavor(Long golesAFavor) {
		this.golesAFavor = golesAFavor;
	}
	public Long getGolesEnContra() {
		return golesEnContra;
	}
	public void setGolesEnContra(Long golesEnContra) {
		this.golesEnContra = golesEnContra;
	}
	public Long getPartidosGanados() {
		return partidosGanados;
	}
	public void setPartidosGanados(Long partidosGanados) {
		this.partidosGanados = partidosGanados;
	}
	public Long getPartidosEmpatados() {
		return partidosEmpatados;
	}
	public void setPartidosEmpatados(Long partidosEmpatados) {
		this.partidosEmpatados = partidosEmpatados;
	}
	public Long getPartidosPerdidos() {
		return partidosPerdidos;
	}
	public void setPartidosPerdidos(Long partidosPerdidos) {
		this.partidosPerdidos = partidosPerdidos;
	}
	public Equipo getEquipo() {
		return equipo;
	}
	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	@Override
	public int compareTo(Estadistica e) {
		
		int compararPuntos = (int)(long)((Estadistica) e).getPuntos(); 
		

		return (int) ( compararPuntos - this.puntos);
	}
}
