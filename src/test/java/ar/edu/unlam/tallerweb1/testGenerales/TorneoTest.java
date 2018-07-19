package ar.edu.unlam.tallerweb1.testGenerales;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Equipo;
import ar.edu.unlam.tallerweb1.modelo.Torneo;

public class TorneoTest extends SpringTest{
	@Test
	@Transactional
	@SuppressWarnings("unchecked")
	public void testCrearUnTorneo() {
		
		Torneo unTorneo = new Torneo();
		unTorneo.setCantidadDeEquipos(new Long(4));
		unTorneo.setDescripcionTorneo("Torneo Test");
		unTorneo.setNombreTorneo("Primer Torneo");
		
		Session session = getSession();
		
		session.saveOrUpdate(unTorneo);
		List<?> resultadoTorneo = session.createCriteria(Torneo.class).add(Restrictions.eq("nombreTorneo", "Primer Torneo").ignoreCase()).list();
		Assert.assertTrue(!resultadoTorneo.isEmpty());
	}
	
	@Test
	@Transactional
	@SuppressWarnings("unchecked")
	public void testGetTorneoById() {
		Torneo unTorneo = new Torneo();
		unTorneo.setCantidadDeEquipos(new Long(4));
		unTorneo.setDescripcionTorneo("Torneo Test");
		unTorneo.setNombreTorneo("Primer Torneo");
		
		Session session = getSession();
		session.saveOrUpdate(unTorneo);
		
		List<?> resultadoTorneo = session.createCriteria(Torneo.class)
				.add(Restrictions.eq("id", unTorneo.getId()))
				.list();
		Assert.assertTrue(!resultadoTorneo.isEmpty());
	}
	
	@Test
	@Transactional
	@SuppressWarnings("unchecked")
	public void testGetTorneosConInscripcionAbierta() {
		
		Torneo TorneoUno = new Torneo();
		TorneoUno.setCantidadDeEquipos(new Long(4));
		TorneoUno.setDescripcionTorneo("Torneo Uno");
		TorneoUno.setNombreTorneo("Primer Torneo");
		Torneo TorneoDos = new Torneo();
		TorneoDos.setCantidadDeEquipos(new Long(6));
		TorneoDos.setDescripcionTorneo("Torneo Dos");
		TorneoDos.setNombreTorneo("Segundo Torneo");
		TorneoDos.setEstado("Finalizado");
		Torneo TorneoTres = new Torneo();
		TorneoTres.setCantidadDeEquipos(new Long(6));
		TorneoTres.setDescripcionTorneo("Torneo Tres");
		TorneoTres.setNombreTorneo("Tercer Torneo");
		
		Session session = getSession();
		
		session.saveOrUpdate(TorneoUno);
		session.saveOrUpdate(TorneoDos);
		session.saveOrUpdate(TorneoTres);
		
		List<?> resultadoTorneo = session.createCriteria(Torneo.class)
				.add(Restrictions.eq("estado", "Inscripcion Abierta"))
				.list();
		Assert.assertTrue(resultadoTorneo.size() == 2);
	}
	
	@Test
	@Transactional
	@SuppressWarnings("unchecked")
	public void testGetTorneosEnCurso(){
		Torneo TorneoUno = new Torneo();
		TorneoUno.setCantidadDeEquipos(new Long(4));
		TorneoUno.setDescripcionTorneo("Torneo Uno");
		TorneoUno.setNombreTorneo("Primer Torneo");
		Torneo TorneoDos = new Torneo();
		TorneoDos.setCantidadDeEquipos(new Long(6));
		TorneoDos.setDescripcionTorneo("Torneo Dos");
		TorneoDos.setNombreTorneo("Segundo Torneo");
		TorneoDos.setEstado("En curso");
		Torneo TorneoTres = new Torneo();
		TorneoTres.setCantidadDeEquipos(new Long(6));
		TorneoTres.setDescripcionTorneo("Torneo Tres");
		TorneoTres.setNombreTorneo("Tercer Torneo");
		
		Session session = getSession();
		
		session.saveOrUpdate(TorneoUno);
		session.saveOrUpdate(TorneoDos);
		session.saveOrUpdate(TorneoTres);
		List<?> resultadoTorneo = session.createCriteria(Torneo.class)
				.add(Restrictions.eq("estado", "En curso"))
				.list();
		Assert.assertTrue(resultadoTorneo.size() == 1);
	}
	
	@Test
	@Transactional
	@SuppressWarnings("unchecked")
	public void testGetTorneosFinalizado() {
		Torneo TorneoUno = new Torneo();
		TorneoUno.setCantidadDeEquipos(new Long(4));
		TorneoUno.setDescripcionTorneo("Torneo Uno");
		TorneoUno.setNombreTorneo("Primer Torneo");
		TorneoUno.setEstado("Finalizado");
		Torneo TorneoDos = new Torneo();
		TorneoDos.setCantidadDeEquipos(new Long(6));
		TorneoDos.setDescripcionTorneo("Torneo Dos");
		TorneoDos.setNombreTorneo("Segundo Torneo");
		TorneoDos.setEstado("Finalizado");
		Torneo TorneoTres = new Torneo();
		TorneoTres.setCantidadDeEquipos(new Long(6));
		TorneoTres.setDescripcionTorneo("Torneo Tres");
		TorneoTres.setNombreTorneo("Tercer Torneo");
		TorneoTres.setEstado("Finalizado");
		
		Session session = getSession();
		
		session.saveOrUpdate(TorneoUno);
		session.saveOrUpdate(TorneoDos);
		session.saveOrUpdate(TorneoTres);
		List<?> resultadoTorneo = session.createCriteria(Torneo.class)
				.add(Restrictions.eq("estado", "Finalizado"))
				.list();
		Assert.assertTrue(resultadoTorneo.size() == 3);
	}
}
