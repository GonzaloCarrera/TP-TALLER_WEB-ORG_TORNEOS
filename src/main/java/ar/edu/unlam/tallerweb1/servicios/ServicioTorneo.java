package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Torneo;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public interface ServicioTorneo {
	void guardarTorneo(Torneo torneo);
	List<Torneo> getTorneosConInscripcionAbierta();
}
