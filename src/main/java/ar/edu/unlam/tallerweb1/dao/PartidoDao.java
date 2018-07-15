package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Equipo;
import ar.edu.unlam.tallerweb1.modelo.Fecha;
import ar.edu.unlam.tallerweb1.modelo.Partido;
import ar.edu.unlam.tallerweb1.modelo.Torneo;

public interface PartidoDao {

	void guardarPartido(Partido partido);

	List<Partido> getListaDePartidosNoFinalizadosByListaDeEquipos(List<Equipo> equipos);

	List<Partido> getListaDePartidosNoFinalizados();
	
	List<Partido> getListaDePartidosDeLaFechaYTorneo(Fecha fecha, Torneo torneo);

	List<Partido> getListaDePartidosDelTorneo(Torneo torneo);

	List<Partido> getListaDePartidosDeLaFecha(Long idFecha);

	Partido getPartidoByIdPartido(Long idPartido);

	List<Partido> getListaDePartidosDelTorneoFinalizados(Torneo torneo);



}
