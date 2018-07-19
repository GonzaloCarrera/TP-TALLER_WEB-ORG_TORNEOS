package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Fecha;
import ar.edu.unlam.tallerweb1.modelo.Torneo;

public interface ServicioFecha {
	void guardarFecha(Fecha fecha);
	List<Fecha> getFechasDeUnTorneo(Torneo torneo);
	List<Fecha> getFechasDeUnTorneoByIdTorneo(Long idTorneo);
	public List<Fecha> getListaDeFechasEnCurso();
	List<Fecha> getListaDeFechasEnPreparacion();
	public Fecha getFechaEnPreparacionDeUnTorneo(Torneo torneo);
	public Boolean machearEquiposDelTorneoParaLaFechaEnPreparacion(Long idTorneo);
	public Integer getCantidadDeFechasActivasDeUnTorneo(Long idTorneo);
	Integer getCantidadDeFechasDeUnTorneo(Torneo torneo);
	Fecha getFechaByIdFecha(Long idFecha);
	List<Fecha> getFechasEnCursoOFinalizadasDeUnTorneoByIdTorneo(Long idTorneo);
}
