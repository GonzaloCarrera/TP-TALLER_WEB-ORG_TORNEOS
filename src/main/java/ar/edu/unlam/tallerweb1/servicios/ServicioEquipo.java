package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Equipo;
import ar.edu.unlam.tallerweb1.modelo.Fecha;

public interface ServicioEquipo {
	void guardarEquipo(Equipo equipo);
	List<Equipo> getListaDeEquiposByIdTorneo(Long idTorneo);
}
