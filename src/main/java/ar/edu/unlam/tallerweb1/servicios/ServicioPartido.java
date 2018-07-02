package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Equipo;
import ar.edu.unlam.tallerweb1.modelo.Partido;

public interface ServicioPartido {
	void guardarPartido(Partido partido);

	List<Partido> getListaDePartidosNoFinalizadosByListaDeEquipos(List<Equipo> equipos);

	List<Partido> getListaDePartidosNoFinalizados();
}
