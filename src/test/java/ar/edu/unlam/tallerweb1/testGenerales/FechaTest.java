package ar.edu.unlam.tallerweb1.testGenerales;

import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Fecha;
import ar.edu.unlam.tallerweb1.modelo.Torneo;

public class FechaTest extends SpringTest{
	@Test
	@Transactional
	@SuppressWarnings("unchecked")
	public void testCrearUnaFecha() {
		
		Torneo unTorneo = new Torneo();
		unTorneo.setCantidadDeEquipos(new Long(4));
		unTorneo.setDescripcionTorneo("Torneo Test");
		unTorneo.setNombreTorneo("Primer Torneo");
		
		Fecha fechaUno = new Fecha();
		fechaUno.setTorneo(unTorneo);
		Calendar cal = Calendar.getInstance(); 
	    cal.setTime(new Date()); 
	    cal.add(Calendar.HOUR_OF_DAY, 1); 
	    Date fecha = cal.getTime();
	    
		fechaUno.setHoraInicio(new Date());
		fechaUno.setHoraFin(fecha);
		
		Session session = getSession();
		
		session.saveOrUpdate(unTorneo);
		session.saveOrUpdate(fechaUno);
		List<?> resultadoFecha = session.createCriteria(Fecha.class).add(Restrictions.eq("id", fechaUno.getId())).list();
		Assert.assertTrue(!resultadoFecha.isEmpty());
	}
	
	@Test
	@Transactional
	@SuppressWarnings("unchecked")
	public void testGetFechasDeUnTorneo() {
		Torneo unTorneo = new Torneo();
		unTorneo.setCantidadDeEquipos(new Long(2));
		unTorneo.setDescripcionTorneo("Torneo Test");
		unTorneo.setNombreTorneo("Primer Torneo");
		
		Fecha fechaUno = new Fecha();
		fechaUno.setTorneo(unTorneo);
		Calendar cal = Calendar.getInstance(); 
	    cal.setTime(new Date()); 
	    cal.add(Calendar.HOUR_OF_DAY, 1); 
	    Date fecha = cal.getTime();
		fechaUno.setHoraInicio(new Date());
		fechaUno.setHoraFin(fecha);
		
		Fecha fechaDos = new Fecha();
		fechaDos.setTorneo(unTorneo);
	    cal.setTime(new Date()); 
	    cal.add(Calendar.HOUR_OF_DAY, 2); 
	    fecha = cal.getTime();
	    fechaDos.setHoraInicio(new Date());
	    fechaDos.setHoraFin(fecha);
		
		Session session = getSession();
		
		session.saveOrUpdate(unTorneo);
		session.saveOrUpdate(fechaUno);
		session.saveOrUpdate(fechaDos);
		
		List<?> resultadoFecha = session.createCriteria(Fecha.class)
				.add(Restrictions.eq("torneo", unTorneo))
				.list();				
		
		Assert.assertTrue(!resultadoFecha.isEmpty());
	}
	
	@Test
	@Transactional
	@SuppressWarnings("unchecked")
	public void testGetFechaByIdFecha(){

		Fecha fechaUno = new Fecha();
		Calendar cal = Calendar.getInstance(); 
	    cal.setTime(new Date()); 
	    cal.add(Calendar.HOUR_OF_DAY, 1); 
	    Date fecha = cal.getTime();
		fechaUno.setHoraInicio(new Date());
		fechaUno.setHoraFin(fecha);
		
		Fecha fechaDos = new Fecha();
	    cal.setTime(new Date()); 
	    cal.add(Calendar.HOUR_OF_DAY, 2); 
	    fecha = cal.getTime();
	    fechaDos.setHoraInicio(new Date());
	    fechaDos.setHoraFin(fecha);
		
		Session session = getSession();
		
		session.saveOrUpdate(fechaUno);
		session.saveOrUpdate(fechaDos);
		
		List<?> resultadoFecha = session.createCriteria(Fecha.class)
			.add(Restrictions.eq("id", fechaUno.getId()))
			.list();
		Assert.assertTrue(!resultadoFecha.isEmpty());
	}
	
	@Test
	@Transactional
	@SuppressWarnings("unchecked")
	public void testGetFechasDeUnTorneoByIdTorneo(){
		Torneo unTorneo = new Torneo();
		unTorneo.setCantidadDeEquipos(new Long(2));
		unTorneo.setDescripcionTorneo("Torneo Test");
		unTorneo.setNombreTorneo("Primer Torneo");
		
		Fecha fechaUno = new Fecha();
		fechaUno.setTorneo(unTorneo);
		Calendar cal = Calendar.getInstance(); 
	    cal.setTime(new Date()); 
	    cal.add(Calendar.HOUR_OF_DAY, 1); 
	    Date fecha = cal.getTime();
		fechaUno.setHoraInicio(new Date());
		fechaUno.setHoraFin(fecha);
		
		Fecha fechaDos = new Fecha();
		fechaDos.setTorneo(unTorneo);
	    cal.setTime(new Date()); 
	    cal.add(Calendar.HOUR_OF_DAY, 2); 
	    fecha = cal.getTime();
	    fechaDos.setHoraInicio(new Date());
	    fechaDos.setHoraFin(fecha);
		
		Session session = getSession();
		
		session.saveOrUpdate(unTorneo);
		session.saveOrUpdate(fechaUno);
		session.saveOrUpdate(fechaDos);
		
		List<?> resultadoFecha = session.createCriteria(Fecha.class)
			.createAlias("torneo", "t")
			.add(Restrictions.eq("t.id", unTorneo.getId()))
			.list();
		
		assertTrue(resultadoFecha.size() == 2);
	}
	
	@Test
	@Transactional
	@SuppressWarnings("unchecked")
	public void testGetListaDeFechasEnCurso(){
		Torneo unTorneo = new Torneo();
		unTorneo.setCantidadDeEquipos(new Long(2));
		unTorneo.setDescripcionTorneo("Torneo Test");
		unTorneo.setNombreTorneo("Primer Torneo");
		
		Fecha fechaUno = new Fecha();
		fechaUno.setTorneo(unTorneo);
		Calendar cal = Calendar.getInstance(); 
	    cal.setTime(new Date()); 
	    cal.add(Calendar.HOUR_OF_DAY, 1); 
	    Date fecha = cal.getTime();
		fechaUno.setHoraInicio(new Date());
		fechaUno.setHoraFin(fecha);
		fechaUno.setEstado("En curso");
		
		Fecha fechaDos = new Fecha();
		fechaDos.setTorneo(unTorneo);
	    cal.setTime(new Date()); 
	    cal.add(Calendar.HOUR_OF_DAY, 2); 
	    fecha = cal.getTime();
	    fechaDos.setHoraInicio(new Date());
	    fechaDos.setHoraFin(fecha);
		
		Session session = getSession();
		
		session.saveOrUpdate(unTorneo);
		session.saveOrUpdate(fechaUno);
		session.saveOrUpdate(fechaDos);
		
	    List<?> resultadoFecha = session.createCriteria(Fecha.class)
			.add(Restrictions.eq("estado", "En curso"))
			.list();
	    assertTrue(resultadoFecha.size() == 1);
	}
	
	@Test
	@Transactional
	@SuppressWarnings("unchecked")
	public void testGetFechaEnPreparacionDeUnTorneo() {
		Torneo unTorneo = new Torneo();
		unTorneo.setCantidadDeEquipos(new Long(2));
		unTorneo.setDescripcionTorneo("Torneo Test");
		unTorneo.setNombreTorneo("Primer Torneo");
		
		Fecha fechaUno = new Fecha();
		fechaUno.setTorneo(unTorneo);
		Calendar cal = Calendar.getInstance(); 
	    cal.setTime(new Date()); 
	    cal.add(Calendar.HOUR_OF_DAY, 1); 
	    Date fecha = cal.getTime();
		fechaUno.setHoraInicio(new Date());
		fechaUno.setHoraFin(fecha);
		fechaUno.setEstado("En curso");
		
		Fecha fechaDos = new Fecha();
		fechaDos.setTorneo(unTorneo);
	    cal.setTime(new Date()); 
	    cal.add(Calendar.HOUR_OF_DAY, 2); 
	    fecha = cal.getTime();
	    fechaDos.setHoraInicio(new Date());
	    fechaDos.setHoraFin(fecha);
		
		Session session = getSession();
		
		session.saveOrUpdate(unTorneo);
		session.saveOrUpdate(fechaUno);
		session.saveOrUpdate(fechaDos);
		
		List<?> resultadoFecha = session.createCriteria(Fecha.class)
				.add(Restrictions.eq("estado", "Preparacion"))
				.add(Restrictions.eq("torneo.id",unTorneo.getId()))
				.list();

		assertTrue(resultadoFecha.size() == 1);
	}
	
	@Test
	@Transactional
	@SuppressWarnings("unchecked")
	public void testGetListaDeFechasEnPreparacion(){
		Fecha fechaUno = new Fecha();
		Calendar cal = Calendar.getInstance(); 
	    cal.setTime(new Date()); 
	    cal.add(Calendar.HOUR_OF_DAY, 1); 
	    Date fecha = cal.getTime();
		fechaUno.setHoraInicio(new Date());
		fechaUno.setHoraFin(fecha);
		fechaUno.setEstado("En curso");
		
		Fecha fechaDos = new Fecha();
	    cal.setTime(new Date()); 
	    cal.add(Calendar.HOUR_OF_DAY, 2); 
	    fecha = cal.getTime();
	    fechaDos.setHoraInicio(new Date());
	    fechaDos.setHoraFin(fecha);
		
		Session session = getSession();
		
		session.saveOrUpdate(fechaUno);
		session.saveOrUpdate(fechaDos);
		
		List<?> resultadoFecha =  session.createCriteria(Fecha.class)
			.add(Restrictions.eq("estado", "Preparacion"))
			.list();
		assertTrue(resultadoFecha.size() == 1);
	}
}
