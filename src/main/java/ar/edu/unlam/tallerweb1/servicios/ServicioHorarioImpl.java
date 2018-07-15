package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.HorarioDao;
import ar.edu.unlam.tallerweb1.modelo.Equipo;
import ar.edu.unlam.tallerweb1.modelo.Fecha;
import ar.edu.unlam.tallerweb1.modelo.Horario;

@Service("servicioHorario")
@Transactional
public class ServicioHorarioImpl implements ServicioHorario {

	@Inject
	private HorarioDao horarioDao;

	@Override
	public void guardarHorario(Horario horario) {
		horarioDao.guardarHorario(horario);		
	}

	@Override
	public List<Horario> getListaDeHorariosPermitirSeleccionTrueByIdEquipo(List<Equipo> equipos) {
		return horarioDao.getListaDeHorariosPermitirSeleccionTrueByIdEquipo(equipos);
	}

	@Override
	public List<Horario> getListaDeHorarioConSeleccionHorarioTrue() {
		return horarioDao.getListaDeHorarioConSeleccionHorarioTrue();
	}

	@Override
	public Horario getHorarioByIdHorario(Long idHorario) {
		return horarioDao.getHorarioByIdHorario(idHorario);
	}

	@Override
	public Horario getHorarioPorFechaYEquipo(Fecha fecha, Equipo equipo) {
		return horarioDao.getHorarioPorFechaYEquipo(fecha, equipo);
	}

	@Override
	public Integer getCantidadDeEquiposQueSeleccionaronHorarioByIdFecha(Long idFecha) {
		return horarioDao.getCantidadDeEquiposQueSeleccionaronHorarioByIdFecha(idFecha);
	}
	
	
}
