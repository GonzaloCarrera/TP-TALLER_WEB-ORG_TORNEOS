package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Equipo;
import ar.edu.unlam.tallerweb1.modelo.Estadistica;
import ar.edu.unlam.tallerweb1.modelo.Fecha;
import ar.edu.unlam.tallerweb1.modelo.Partido;
import ar.edu.unlam.tallerweb1.modelo.Torneo;

public interface ServicioPartido {
	void guardarPartido(Partido partido);

	List<Partido> getListaDePartidosNoFinalizadosByListaDeEquipos(List<Equipo> equipos);

	List<Partido> getListaDePartidosNoFinalizados();
	
	List<Partido> getListaDePartidosDeLaFechaYTorneo(Fecha fecha, Torneo torneo);
	
	List<Partido> getListaDePartidosDelTorneo(Torneo torneo);
	
	List<Partido> getListaDePartidosDeLaFecha(Long idFecha);
	
	Partido getPartidoByIdPartido(Long idPartido);

	List<Estadistica> getTablaDePosicionesByTorneo(Torneo torneo);
	List<Partido> getListaDePartidosDelTorneoFinalizados(Torneo torneo);

}
