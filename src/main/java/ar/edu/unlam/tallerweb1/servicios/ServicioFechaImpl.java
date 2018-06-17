package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.FechaDao;
import ar.edu.unlam.tallerweb1.modelo.Fecha;
import ar.edu.unlam.tallerweb1.modelo.Torneo;

@Service("servicioFecha")
@Transactional
public class ServicioFechaImpl implements ServicioFecha{

	@Inject
	private FechaDao fechaDao;

	@Override
	public void guardarFecha(Fecha fecha) {
		fechaDao.guardarFecha(fecha);
	}

	@Override
	public List<Fecha> getFechasDeUnTorneo(Torneo torneo) {
		return fechaDao.getFechasDeUnTorneo(torneo);
	}
}
