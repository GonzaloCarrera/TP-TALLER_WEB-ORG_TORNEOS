package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Equipo;
import ar.edu.unlam.tallerweb1.modelo.Fecha;
import ar.edu.unlam.tallerweb1.modelo.Horario;

public interface HorarioDao {

	void guardarHorario(Horario horario);

	List<Horario> getListaDeHorariosPermitirSeleccionTrueByIdEquipo(List<Equipo> equipos);

	List<Horario> getListaDeHorarioConSeleccionHorarioTrue();

	Horario getHorarioByIdHorario(Long idHorario);

	Horario getHorarioPorFechaYEquipo(Fecha fecha, Equipo equipo);
}
