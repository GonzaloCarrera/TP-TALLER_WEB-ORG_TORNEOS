package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Equipo;
import ar.edu.unlam.tallerweb1.modelo.Partido;

public interface PartidoDao {

	void guardarPartido(Partido partido);

	List<Partido> getListaDePartidosNoFinalizadosByListaDeEquipos(List<Equipo> equipos);

	List<Partido> getListaDePartidosNoFinalizados();

}
