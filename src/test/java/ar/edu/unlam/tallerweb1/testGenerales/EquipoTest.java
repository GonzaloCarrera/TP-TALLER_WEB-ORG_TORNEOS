package ar.edu.unlam.tallerweb1.testGenerales;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Equipo;
import ar.edu.unlam.tallerweb1.modelo.Jugador;
import ar.edu.unlam.tallerweb1.modelo.Torneo;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import java.math.BigInteger;

public class EquipoTest extends SpringTest{
	@Test
	@Transactional
	@SuppressWarnings("unchecked")
	public void crearUnEquipoTest() {
		Equipo equipo = new Equipo();
		equipo.setNombreEquipo("Equipo Test");
		Usuario unUsuario = new Usuario();

		unUsuario.setUsername("Nombre Test");
		unUsuario.setPassword("123456");
		unUsuario.setEsAdmin(true);
		unUsuario.setEmail("unCorreo@fulbito.com");
		Session session = getSession();
		session.saveOrUpdate(unUsuario);
		equipo.setUsuario(unUsuario);
		
		session.saveOrUpdate(equipo);
		
		Jugador jugador1 = new Jugador();
		Jugador jugador2 = new Jugador();
		Jugador jugador3 = new Jugador();
		Jugador jugador4 = new Jugador();
		Jugador jugador5 = new Jugador();
		Jugador jugador6 = new Jugador();
		Jugador jugador7 = new Jugador();
		Jugador jugador8 = new Jugador();
		Jugador jugador9 = new Jugador();
		Jugador jugador10 = new Jugador();
		jugador1.setNombre("nombreJugador1");
		jugador1.setEquipo(equipo);
		session.saveOrUpdate(jugador1);
		jugador2.setNombre("nombreJugador2");
		jugador2.setEquipo(equipo);
		session.saveOrUpdate(jugador2);
		jugador3.setNombre("nombreJugador3");
		jugador3.setEquipo(equipo);
		session.saveOrUpdate(jugador3);
		jugador4.setNombre("nombreJugador4");
		jugador4.setEquipo(equipo);
		session.saveOrUpdate(jugador4);
		jugador5.setNombre("nombreJugador5");
		jugador5.setEquipo(equipo);
		session.saveOrUpdate(jugador5);
		jugador6.setNombre("nombreJugador6");
		jugador6.setEquipo(equipo);
		session.saveOrUpdate(jugador6);
		jugador7.setNombre("nombreJugador7");
		jugador7.setEquipo(equipo);
		session.saveOrUpdate(jugador7);
		jugador8.setNombre("nombreJugador8");
		jugador8.setEquipo(equipo);
		session.saveOrUpdate(jugador8);
		jugador9.setNombre("nombreJugador9");
		jugador9.setEquipo(equipo);
		session.saveOrUpdate(jugador9);
		jugador10.setNombre("nombreJugador10");
		jugador10.setEquipo(equipo);
		session.saveOrUpdate(jugador10);
		
		
		List<?> resultadoJugador = session.createCriteria(Jugador.class,"jugador")
				.createAlias("jugador.equipo", "e")
				.add(Restrictions.eq("e.nombreEquipo", "Equipo Test").ignoreCase()).list();
		Assert.assertTrue(!resultadoJugador.isEmpty());
	}
	
	@Test
	@Transactional
	@SuppressWarnings("unchecked")
	public void obtenerEquipoById() {
		Equipo equipo = new Equipo();
		equipo.setNombreEquipo("Equipo Test");

		Session session = getSession();
		session.saveOrUpdate(equipo);
		
		List<?> resultadoJugador =  session.createCriteria(Equipo.class)
				.add(Restrictions.eq("id", equipo.getId()))
				.list();
		
		Assert.assertTrue(!resultadoJugador.isEmpty());
	}

	@Test
	@Transactional
	@SuppressWarnings("unchecked")
	public void testGetListaDeEquiposByIdTorneo() {
		Torneo unTorneo = new Torneo();
		unTorneo.setCantidadDeEquipos(new Long(4));
		unTorneo.setDescripcionTorneo("Torneo Test");
		unTorneo.setNombreTorneo("Primer Torneo");
		
		Equipo equipo = new Equipo();
		equipo.setNombreEquipo("Equipo Test");
		List<Torneo> torneos = new ArrayList();
		torneos.add(unTorneo);
		equipo.setTorneos(torneos);

		Session session = getSession();
		session.saveOrUpdate(equipo);

		List<?> resultadoTorneo =  session.createCriteria(Equipo.class)
				.createAlias("torneos", "t")
				.add(Restrictions.eq("t.id", equipo.getTorneos().get(0).getId()))
				.list();
		
		Assert.assertTrue(!resultadoTorneo.isEmpty());
	}
	
	@Test
	@Transactional
	@SuppressWarnings("unchecked")
	public void testGetListaDeEquiposCompleta() {
		
		Equipo equipo = new Equipo();
		equipo.setNombreEquipo("Equipo Test1");
		Equipo equipo2 = new Equipo();
		equipo2.setNombreEquipo("Equipo Test2");
		Equipo equipo3 = new Equipo();
		equipo3.setNombreEquipo("Equipo Test3");
		
		Session session = getSession();
		session.saveOrUpdate(equipo);
		session.saveOrUpdate(equipo2);
		session.saveOrUpdate(equipo3);
		
		List<Equipo> equipos = getSession().createCriteria(Equipo.class)
				.list();
	
		List<Equipo> listaDeEquipos = new ArrayList<Equipo>();
		for(Equipo e : equipos){
			if(!listaDeEquipos.contains(e)){
				listaDeEquipos.add(e);
			}
		}
		Assert.assertTrue(listaDeEquipos.size() == 3);
	}
	
	@Test
	@Transactional
	@SuppressWarnings("unchecked")
	public void testgetListaDeEquiposByIdUsuario() {
		Session session = getSession();
		Equipo equipo = new Equipo();
		equipo.setNombreEquipo("Equipo Test1");
		Usuario unUsuario = new Usuario();

		unUsuario.setUsername("Usuario 1");
		unUsuario.setPassword("123456");
		unUsuario.setEsAdmin(true);
		unUsuario.setEmail("unCorreo4@fulbito.com");
		session.saveOrUpdate(unUsuario);
		equipo.setUsuario(unUsuario);
		session.saveOrUpdate(equipo);
		Equipo equipo2 = new Equipo();
		equipo2.setNombreEquipo("Equipo Test2");
		Usuario unUsuario2 = new Usuario();
		unUsuario2.setUsername("Usuario 2");
		unUsuario2.setPassword("123456");
		unUsuario2.setEsAdmin(true);
		unUsuario2.setEmail("unCorreo2@fulbito.com");
		session.saveOrUpdate(unUsuario2);
		equipo2.setUsuario(unUsuario2);
		session.saveOrUpdate(equipo2);
		Equipo equipo3 = new Equipo();
		equipo3.setNombreEquipo("Equipo Test3");
		Usuario unUsuario3 = new Usuario();
		unUsuario3.setUsername("Usuario 3");
		unUsuario3.setPassword("123456");
		unUsuario3.setEsAdmin(true);
		unUsuario3.setEmail("unCorreo3@fulbito.com");
		session.saveOrUpdate(unUsuario3);
		equipo3.setUsuario(unUsuario3);
		
		session.saveOrUpdate(equipo3);
		
		List<Equipo> equipos = getSession().createCriteria(Equipo.class)
				.list();
		List<Equipo> listaDeEquipos = new ArrayList<Equipo>();
		for(Equipo e : equipos){
			if(!listaDeEquipos.contains(e)){
				listaDeEquipos.add(e);
			}
		}
		List<Equipo> equiposLista = listaDeEquipos;
		List<Equipo> equiposDelUsuario= new ArrayList<Equipo>();
		for(Equipo e : equiposLista){
			if(e.getUsuario().getId()==equipo2.getUsuario().getId()){
				if(!equiposDelUsuario.contains(e)){
					equiposDelUsuario.add(e);
				}
			}
		}
		Assert.assertTrue(equiposDelUsuario.size() == 1);
	}
	
	@Test
	@Transactional
	@SuppressWarnings("unchecked")
	public void testGetCantidadDeEquiposRegistradorEnElTorneoPorElUsuario() {
		Torneo unTorneo = new Torneo();
		unTorneo.setCantidadDeEquipos(new Long(4));
		unTorneo.setDescripcionTorneo("Torneo Test");
		unTorneo.setNombreTorneo("Primer Torneo");
		
		Usuario unUsuario = new Usuario();

		unUsuario.setUsername("Nombre Test");
		unUsuario.setPassword("123456");
		unUsuario.setEsAdmin(true);
		unUsuario.setEmail("unCorreo@fulbito.com");
		
		Equipo equipo = new Equipo();
		equipo.setNombreEquipo("Equipo Test");
		List<Torneo> torneos = new ArrayList();
		torneos.add(unTorneo);
		equipo.setTorneos(torneos);
	    equipo.setUsuario(unUsuario);

		Equipo equipoDos = new Equipo();
		equipoDos.setNombreEquipo("Segundo Equipo");
		equipoDos.setTorneos(torneos);
		equipoDos.setUsuario(unUsuario);
		
		Session session = getSession();
		session.saveOrUpdate(unUsuario);
		session.saveOrUpdate(equipo);

		List<Equipo> equipos =  session.createCriteria(Equipo.class)
				.createAlias("torneos", "t")
				.add(Restrictions.eq("t.id", equipo.getTorneos().get(0).getId()))
				.list();

		Integer cont = 0;
		for(Equipo e : equipos){
			if(e.getUsuario().getId()==unUsuario.getId()){
				cont++;
			}
		}
		Assert.assertTrue(cont > 0);
	}
}



