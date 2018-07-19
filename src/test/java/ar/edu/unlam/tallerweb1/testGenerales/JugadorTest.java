package ar.edu.unlam.tallerweb1.testGenerales;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Equipo;
import ar.edu.unlam.tallerweb1.modelo.Jugador;

public class JugadorTest extends SpringTest{
	@Test
	@Transactional
	@SuppressWarnings("unchecked")
	public void testCrearJugador() {
		Jugador unJugador = new Jugador();
		Equipo unEquipo = new Equipo();
		unJugador.setNombre("Jugador 1");
		unJugador.setEquipo(unEquipo);
		Session session = getSession();
		session.saveOrUpdate(unEquipo);
		session.saveOrUpdate(unJugador);
		
		List<?> resultadoJugador = session.createCriteria(Jugador.class)
				.add(Restrictions.eq("id", unJugador.getId())).list();
		assertTrue(!resultadoJugador.isEmpty());
	}

}
