package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Equipo;
import ar.edu.unlam.tallerweb1.modelo.Torneo;

public interface EquipoDao {

	void guardarEquipo(Equipo equipo);

	List<Equipo> getListaDeEquiposByIdTorneo(Long idTorneo);

	List<Equipo> getListaDeEquiposByIdUsuario(Long idUsuario);

	Equipo getEquipoById(Long idEquipo);

}
