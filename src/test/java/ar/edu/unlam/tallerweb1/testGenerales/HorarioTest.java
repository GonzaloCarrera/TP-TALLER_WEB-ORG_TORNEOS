package ar.edu.unlam.tallerweb1.testGenerales;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Equipo;
import ar.edu.unlam.tallerweb1.modelo.Fecha;
import ar.edu.unlam.tallerweb1.modelo.Horario;
import ar.edu.unlam.tallerweb1.modelo.Torneo;

public class HorarioTest extends SpringTest{
	@Test
	@Transactional
	@SuppressWarnings("unchecked")
	public void testGuardarHorario() {
		
		Horario unHorario = new Horario();
		unHorario.setHoraInicio(new Date());
		Calendar cal = Calendar.getInstance(); 
	    cal.setTime(new Date()); 
	    cal.add(Calendar.HOUR_OF_DAY, 5); 
	    Date horaFin = cal.getTime();
		unHorario.setHoraFin(horaFin);
		
		Session session = getSession();
		session.saveOrUpdate(unHorario);
		
		List<?> resultadoHorario = session.createCriteria(Horario.class).add(Restrictions.eq("id", unHorario.getId())).list();
		Assert.assertTrue(!resultadoHorario.isEmpty());
	}
	
	@Test
	@Transactional
	@SuppressWarnings("unchecked")
	public void getListaDeHorarioConSeleccionHorarioTrue() {
		Horario unHorario = new Horario();
		unHorario.setHoraInicio(new Date());
		Calendar cal = Calendar.getInstance(); 
	    cal.setTime(new Date()); 
	    cal.add(Calendar.HOUR_OF_DAY, 5); 
	    Date horaFin = cal.getTime();
		unHorario.setHoraFin(horaFin);
		
		Horario HorarioDos = new Horario();
		HorarioDos.setHoraInicio(new Date());
		cal = Calendar.getInstance(); 
	    cal.setTime(new Date()); 
	    cal.add(Calendar.HOUR_OF_DAY, 6); 
	    horaFin = cal.getTime();
	    HorarioDos.setHoraFin(horaFin);
	    HorarioDos.setPermitirSeleccionHorario(false);
		
		Session session = getSession();
		session.saveOrUpdate(unHorario);
		session.saveOrUpdate(HorarioDos);
		
		List<Horario> listaDeHorarios = new ArrayList<Horario>();
		List<Horario> horarios = session.createCriteria(Horario.class)
				.add(Restrictions.eq("permitirSeleccionHorario", true))
				.list();
		for(Horario h : horarios){
			if(!listaDeHorarios.contains(h)){
				listaDeHorarios.add(h);
			}
		}
		Assert.assertTrue(listaDeHorarios.size() == 1);
	}
	
	@Test
	@Transactional
	@SuppressWarnings("unchecked")
	public void getHorarioPorFechaYEquipo() {
		Fecha unaFecha = new Fecha();
		Fecha fechaDos = new Fecha();
		Equipo unEquipo = new Equipo();
		Equipo EquipoDos = new Equipo();
		
		Horario unHorario = new Horario();
		unHorario.setHoraInicio(new Date());
		Calendar cal = Calendar.getInstance(); 
	    cal.setTime(new Date()); 
	    cal.add(Calendar.HOUR_OF_DAY, 5); 
	    Date horaFin = cal.getTime();
		unHorario.setHoraFin(horaFin);
		unHorario.setFecha(unaFecha);
		unHorario.setEquipo(unEquipo);
		
		Horario HorarioDos = new Horario();
		HorarioDos.setHoraInicio(new Date());
		cal = Calendar.getInstance(); 
	    cal.setTime(new Date()); 
	    cal.add(Calendar.HOUR_OF_DAY, 6); 
	    horaFin = cal.getTime();
	    HorarioDos.setHoraFin(horaFin);
	    HorarioDos.setFecha(fechaDos);
	    HorarioDos.setEquipo(EquipoDos);
		
		Session session = getSession();
		session.saveOrUpdate(unaFecha);
		session.saveOrUpdate(fechaDos);
		session.saveOrUpdate(unEquipo);
		session.saveOrUpdate(EquipoDos);
		session.saveOrUpdate(unHorario);
		session.saveOrUpdate(HorarioDos);
		
	    List<?> resultadoHorario = session.createCriteria(Horario.class)
				.add(Restrictions.eq("fecha", unaFecha))
				.add(Restrictions.eq("equipo", unEquipo))
				.list();
	    Assert.assertTrue(resultadoHorario.size() == 1);
	}
	
	@Test
	@Transactional
	@SuppressWarnings("unchecked")
	public void testGetCantidadDeEquiposQueSeleccionaronHorarioByIdFecha() {
		Fecha unaFecha = new Fecha();
		Fecha fechaDos = new Fecha();
		Equipo unEquipo = new Equipo();
		Equipo EquipoDos = new Equipo();
		
		Horario unHorario = new Horario();
		unHorario.setHoraInicio(new Date());
		Calendar cal = Calendar.getInstance(); 
	    cal.setTime(new Date()); 
	    cal.add(Calendar.HOUR_OF_DAY, 5); 
	    Date horaFin = cal.getTime();
		unHorario.setHoraFin(horaFin);
		unHorario.setFecha(unaFecha);
		unHorario.setEquipo(unEquipo);
		unHorario.setPermitirSeleccionHorario(false);
		
		Horario HorarioDos = new Horario();
		HorarioDos.setHoraInicio(new Date());
		cal = Calendar.getInstance(); 
	    cal.setTime(new Date()); 
	    cal.add(Calendar.HOUR_OF_DAY, 6); 
	    horaFin = cal.getTime();
	    HorarioDos.setHoraFin(horaFin);
	    HorarioDos.setFecha(fechaDos);
	    HorarioDos.setEquipo(EquipoDos);
		
		Session session = getSession();
		session.saveOrUpdate(unaFecha);
		session.saveOrUpdate(fechaDos);
		session.saveOrUpdate(unEquipo);
		session.saveOrUpdate(EquipoDos);
		session.saveOrUpdate(unHorario);
		session.saveOrUpdate(HorarioDos);
		
		List<Horario> listaDeHorarios = new ArrayList<Horario>();
		List<Horario> horarios = session.createCriteria(Horario.class)
				.createAlias("fecha", "f")
				.add(Restrictions.eq("f.id", unaFecha.getId()))
				.add(Restrictions.eq("permitirSeleccionHorario", false))
				.list();
		for(Horario h : horarios){
			if(!listaDeHorarios.contains(h)){
				listaDeHorarios.add(h);
			}
		}
		assertTrue(listaDeHorarios.size() == 1); 
	}
	
	@Test
	@Transactional
	@SuppressWarnings("unchecked")
	public void testGetListaDeHorariosPermitirSeleccionTrueByIdEquipo() {
		Equipo unEquipo = new Equipo();
		Equipo EquipoDos = new Equipo();
		Horario unHorario = new Horario();
		unHorario.setHoraInicio(new Date());
		Calendar cal = Calendar.getInstance(); 
	    cal.setTime(new Date()); 
	    cal.add(Calendar.HOUR_OF_DAY, 5); 
	    Date horaFin = cal.getTime();
		unHorario.setHoraFin(horaFin);
		unHorario.setEquipo(unEquipo);
		
		Horario HorarioDos = new Horario();
		HorarioDos.setHoraInicio(new Date());
		cal = Calendar.getInstance(); 
	    cal.setTime(new Date()); 
	    cal.add(Calendar.HOUR_OF_DAY, 6); 
	    horaFin = cal.getTime();
	    HorarioDos.setHoraFin(horaFin);
	    HorarioDos.setEquipo(EquipoDos);
	    HorarioDos.setPermitirSeleccionHorario(false);
	    List<Equipo> equipos = new ArrayList<>();
	    equipos.add(unEquipo);
	    equipos.add(EquipoDos);
	    
		Session session = getSession();
		session.saveOrUpdate(unEquipo);
		session.saveOrUpdate(EquipoDos);
		session.saveOrUpdate(unHorario);
		session.saveOrUpdate(HorarioDos);
		
		List<Horario> listaDeHorarios = new ArrayList<Horario>();
		List<Horario> horarios = session.createCriteria(Horario.class)
				.add(Restrictions.eq("permitirSeleccionHorario", true))
				.list();
		for(Horario h : horarios){
			if(!listaDeHorarios.contains(h)){
				listaDeHorarios.add(h);
			}
		}
		
		List<Horario> horariosPermitidos = listaDeHorarios;
		List<Horario> listaDeHorariosDelUsuario = new ArrayList<Horario>();
		for(Horario h : horariosPermitidos){
			for(Equipo e : equipos){
				if(h.getEquipo().getId()==e.getId()){
					listaDeHorariosDelUsuario.add(h);
				}
			}
		}
		assertTrue(listaDeHorariosDelUsuario.size() == 1); 
	}
}
