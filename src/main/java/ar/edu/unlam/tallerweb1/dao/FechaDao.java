package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Fecha;
import ar.edu.unlam.tallerweb1.modelo.Torneo;

public interface FechaDao {

	void guardarFecha(Fecha fecha);
	List<Fecha> getFechasDeUnTorneo(Torneo torneo);
	List<Fecha> getFechasDeUnTorneoByIdTorneo(Long idTorneo);
	public List<Fecha> getListaDeFechasEnCurso();
	List<Fecha> getListaDeFechasEnPreparacion();
	Fecha getFechaActivaDeUnTorneo(Torneo torneo);
}
