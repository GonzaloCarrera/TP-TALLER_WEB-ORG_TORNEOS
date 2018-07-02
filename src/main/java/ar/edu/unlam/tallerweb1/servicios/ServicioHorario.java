package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Equipo;
import ar.edu.unlam.tallerweb1.modelo.Horario;

public interface ServicioHorario {

	void guardarHorario(Horario horario);
	List<Horario> getListaDeHorariosPermitirSeleccionTrueByIdEquipo(List<Equipo> equipos);
	List<Horario> getListaDeHorarioConSeleccionHorarioTrue();
	Horario getHorarioByIdHorario(Long idHorario);
}
