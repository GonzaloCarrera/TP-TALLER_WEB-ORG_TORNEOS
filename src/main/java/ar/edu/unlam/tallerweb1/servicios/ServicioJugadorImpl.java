package ar.edu.unlam.tallerweb1.servicios;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.JugadorDao;
import ar.edu.unlam.tallerweb1.dao.PartidoDao;
import ar.edu.unlam.tallerweb1.modelo.Jugador;

@Service("servicioJugador")
@Transactional
public class ServicioJugadorImpl implements ServicioJugador{
	
		@Inject
		private JugadorDao jugadorDao;

		@Override
		public void guardarJugador(Jugador jugador) {
			jugadorDao.guardarJugador(jugador);			
		}
}
