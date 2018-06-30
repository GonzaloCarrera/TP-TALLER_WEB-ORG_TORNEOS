package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.EquipoDao;
import ar.edu.unlam.tallerweb1.dao.FechaDao;
import ar.edu.unlam.tallerweb1.modelo.Equipo;

@Service("servicioEquipo")
@Transactional
public class ServicioEquipoImpl implements ServicioEquipo{

	@Inject
	private EquipoDao equipoDao;

	@Override
	public void guardarEquipo(Equipo equipo) {
		equipoDao.guardarEquipo(equipo);	
	}

	@Override
	public List<Equipo> getListaDeEquiposByIdTorneo(Long idTorneo) {
		return equipoDao.getListaDeEquiposByIdTorneo(idTorneo);
	}

	@Override
	public List<Equipo> getListaDeEquiposByIdUsuario(Long idUsuario) {
		return equipoDao.getListaDeEquiposByIdUsuario(idUsuario);
	}

	@Override
	public Equipo getEquipoById(Long idEquipo) {
		return equipoDao.getEquipoById(idEquipo);
	}
}
