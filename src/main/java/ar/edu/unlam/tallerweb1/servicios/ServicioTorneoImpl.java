package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.TorneoDao;
import ar.edu.unlam.tallerweb1.modelo.Torneo;

@Service("servicioTorneo")
@Transactional
public class ServicioTorneoImpl implements ServicioTorneo{

	@Inject
	private TorneoDao torneoDao;

	@Override
	public void guardarTorneo(Torneo torneo) {
		torneoDao.guardarTorneo(torneo);
	}

	@Override
	public List<Torneo> getTorneosConInscripcionAbierta() {
		return torneoDao.getTorneosConInscripcionAbierta();
	}
	
	@Override
	public List<Torneo> getTorneosEnCurso() {
		return torneoDao.getTorneosEnCurso();
	}
	
	@Override
	public List<Torneo> getTorneosFinalizado() {
		return torneoDao.getTorneosFinalizado();
	}

	@Override
	public Torneo getTorneoById(Long idTorneo) {
		return torneoDao.getTorneoById(idTorneo);
	}

	@Override
	public List<Torneo> getTorneosEnCursoOFinalizados() {
		return torneoDao.getTorneosEnCursoOFinalizados();
	}
}
