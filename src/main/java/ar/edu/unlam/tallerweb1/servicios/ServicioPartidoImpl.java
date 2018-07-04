package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.PartidoDao;
import ar.edu.unlam.tallerweb1.modelo.Equipo;
import ar.edu.unlam.tallerweb1.modelo.Fecha;
import ar.edu.unlam.tallerweb1.modelo.Partido;
import ar.edu.unlam.tallerweb1.modelo.Torneo;
@Service("servicioPartido")
@Transactional
public class ServicioPartidoImpl implements ServicioPartido {

	@Inject
	private PartidoDao partidoDao;

	@Override
	public void guardarPartido(Partido partido) {
		partidoDao.guardarPartido(partido);		
	}

	@Override
	public List<Partido> getListaDePartidosNoFinalizadosByListaDeEquipos(List<Equipo> equipos) {
		return partidoDao.getListaDePartidosNoFinalizadosByListaDeEquipos(equipos);
	}

	@Override
	public List<Partido> getListaDePartidosNoFinalizados() {
		return partidoDao.getListaDePartidosNoFinalizados();
	}

	@Override
	public List<Partido> getListaDePartidosDeLaFechaYTorneo(Fecha fecha, Torneo torneo) {
		return partidoDao.getListaDePartidosDeLaFechaYTorneo(fecha, torneo);
	}

	@Override
	public List<Partido> getListaDePartidosDelTorneo(Torneo torneo) {
		return partidoDao.getListaDePartidosDelTorneo(torneo);
	}
}
