package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.dao.EquipoDao;
import ar.edu.unlam.tallerweb1.modelo.Equipo;

public interface ServicioEquipo {
	void guardarEquipo(Equipo equipo);
	List<Equipo> getListaDeEquiposByIdTorneo(Long idTorneo);
	List<Equipo> getListaDeEquiposByIdUsuario(Long idUsuario);
	Equipo getEquipoById(Long idEquipo);
	public Integer getCantidadDeEquiposRegistradorEnElTorneoPorElUsuario(Long idTorneo, Long idUsuario);
	void setDao(EquipoDao equipo);
}
