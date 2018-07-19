package ar.edu.unlam.tallerweb1.testGenerales;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Equipo;
import ar.edu.unlam.tallerweb1.modelo.Fecha;
import ar.edu.unlam.tallerweb1.modelo.Partido;
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
	
	@Test
	@Transactional
	@SuppressWarnings("unchecked")
	public void testMachearEquiposDelTorneoParaLaFechaEnPreparacion(){
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
		
		Torneo unTorneo = new Torneo();
		unTorneo.setCantidadDeEquipos(new Long(4));
		unTorneo.setDescripcionTorneo("Torneo Test");
		unTorneo.setNombreTorneo("Primer Torneo");
		
        /*Equipo equipo = new Equipo();
		equipo.setNombreEquipo("Equipo Test");
		List<Torneo> torneos = new ArrayList();
		torneos.add(unTorneo);
		equipo.setTorneos(torneos);
		session.saveOrUpdate(equipo);*/

		
		Torneo torneo = (Torneo) session.createCriteria(Torneo.class)
				.add(Restrictions.eq("id", unTorneo.getId()))
				.uniqueResult();
		
		List<Partido> partidosDeLaFechaNueva = new ArrayList<>();
		Map<Equipo,List<Equipo>> mapaDeEquiposDisponibles = new HashMap<Equipo,List<Equipo>>();
		
		Equipo unEquipo = new Equipo();
		Equipo EquipoDos = new Equipo();
		Equipo EquipoTres = new Equipo();
		Equipo EquipoCuatro = new Equipo();
		
		List<Equipo> equipos = new ArrayList<Equipo>();
		equipos.add(unEquipo);
		equipos.add(EquipoDos);
		equipos.add(EquipoTres);
		equipos.add(EquipoCuatro);
		
		Fecha unaFecha = new Fecha();
		Torneo TorneoDos = new Torneo();
		unaFecha.setTorneo(unTorneo);
		fechaDos.setTorneo(TorneoDos);
		
		Partido unPartido = new Partido();
		
		unPartido.setEquipo1(unEquipo);
		unPartido.setEquipo2(EquipoDos);
		unPartido.setFecha(unaFecha);
				
		Partido PartidoDos = new Partido();
		PartidoDos.setEquipo1(EquipoTres);
		PartidoDos.setEquipo2(EquipoCuatro);
		PartidoDos.setFecha(fechaDos);
		PartidoDos.setFinalizado(true);
		
		session.saveOrUpdate(unEquipo);
		session.saveOrUpdate(EquipoDos);
		session.saveOrUpdate(EquipoTres);
		session.saveOrUpdate(EquipoCuatro);
		
		session.saveOrUpdate(unaFecha);
		session.saveOrUpdate(fechaDos);
		session.saveOrUpdate(unTorneo);
		session.saveOrUpdate(TorneoDos);
		
		session.saveOrUpdate(unPartido);
		session.saveOrUpdate(PartidoDos);
		
		
		List<Partido> listaDePartidos = new ArrayList<Partido>();
		List<Partido> partidosLista = getSession().createCriteria(Partido.class)
				.createAlias("fecha", "f")
				.add(Restrictions.eq("f.torneo", unTorneo))
				.list();
		for(Partido p : partidosLista){
			if(!listaDePartidos.contains(p)){
				listaDePartidos.add(p);
			}
		}	
		
		List<Partido> partidos = listaDePartidos;
		List<Partido> partidosGuardados = listaDePartidos;
		for (Equipo equipo : equipos) {
			List<Equipo> equiposAux = new ArrayList<>(equipos);
			List<Equipo> equiposASacar = this.equiposQueJugaronConEquipo(equipo, partidos);
			equiposAux.removeAll(equiposASacar);
			mapaDeEquiposDisponibles.put(equipo, equiposAux);
		}
		
		
		Fecha fechaResultado = (Fecha) session.createCriteria(Fecha.class)
				.add(Restrictions.eq("estado", "Preparacion"))
				.add(Restrictions.eq("torneo.id",unTorneo.getId()))
				.uniqueResult();
		
/*		Boolean condicion = false;
		do{
			//condicion = macheo(equipos, partidosDeLaFechaNueva, mapaDeEquiposDisponibles, fechaResultado);
		}while(!condicion);
		
		for(Partido partido : partidosDeLaFechaNueva) {
			partidosGuardados.add(partido);
		}*/

	}
	
	private List<Equipo> equiposQueJugaronConEquipo(Equipo equipo, List<Partido> partidos) {
		Set<Equipo> equipos = new HashSet<>();
		equipos.add(equipo);
		for (Partido partido : partidos) {
			if(partido.getEquipo1().equals(equipo) || partido.getEquipo2().equals(equipo)) {
				equipos.add(partido.getEquipo1());
				equipos.add(partido.getEquipo2());
			}
		}
		return new ArrayList<>(equipos);
	}
}
