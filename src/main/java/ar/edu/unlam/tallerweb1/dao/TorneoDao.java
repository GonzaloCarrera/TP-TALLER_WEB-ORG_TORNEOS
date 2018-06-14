package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Torneo;

public interface TorneoDao {

	void guardarTorneo(Torneo torneo);
	List<Torneo> getTorneosConInscripcionAbierta();
}
