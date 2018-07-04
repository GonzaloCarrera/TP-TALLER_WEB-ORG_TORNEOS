package ar.edu.unlam.tallerweb1.dao;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Jugador;
import ar.edu.unlam.tallerweb1.modelo.Partido;

@Repository("jugadorDao")
public class JugadorDaoImpl implements JugadorDao {

	@Inject
    private SessionFactory sessionFactory;
	
	@Override
	public void guardarJugador(Jugador jugador) {
		final Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(jugador);
	}
}
