package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.HorarioDao;
import ar.edu.unlam.tallerweb1.dao.PartidoDao;
import ar.edu.unlam.tallerweb1.modelo.Equipo;
import ar.edu.unlam.tallerweb1.modelo.Partido;
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
}
