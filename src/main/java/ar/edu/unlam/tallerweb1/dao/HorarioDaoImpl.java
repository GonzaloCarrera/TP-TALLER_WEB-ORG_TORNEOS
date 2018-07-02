package ar.edu.unlam.tallerweb1.dao;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Equipo;
import ar.edu.unlam.tallerweb1.modelo.Fecha;
import ar.edu.unlam.tallerweb1.modelo.Horario;
import ar.edu.unlam.tallerweb1.modelo.Torneo;

@Repository("horarioDao")
public class HorarioDaoImpl implements HorarioDao {

	@Inject
    private SessionFactory sessionFactory;

	@Override
	public void guardarHorario(Horario horario) {
		final Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(horario);
	}
	
	@Override
	public List<Horario> getListaDeHorariosPermitirSeleccionTrueByIdEquipo(List<Equipo> equipos) {
		List<Horario> horarios = this.getListaDeHorarioConSeleccionHorarioTrue();
		List<Horario> listaDeHorariosDelUsuario = new ArrayList<Horario>();
		for(Horario h : horarios){
			for(Equipo e : equipos){
				if(h.getEquipo().getId()==e.getId()){
					listaDeHorariosDelUsuario.add(h);
				}
			}
		}
		return listaDeHorariosDelUsuario;
	}
	
	@Override
	public List<Horario> getListaDeHorarioConSeleccionHorarioTrue() {
		final Session session = sessionFactory.getCurrentSession();
		List<Horario> listaDeHorarios = new ArrayList<Horario>();
		List<Horario> horarios = session.createCriteria(Horario.class)
				.add(Restrictions.eq("permitirSeleccionHorario", true))
				.list();
		for(Horario h : horarios){
			if(!listaDeHorarios.contains(h)){
				listaDeHorarios.add(h);
			}
		}
		return listaDeHorarios;
	}
	
	@Override
	public Horario getHorarioByIdHorario(Long idHorario) {
		final Session session = sessionFactory.getCurrentSession();
		return (Horario) session.createCriteria(Horario.class)
				.add(Restrictions.eq("id", idHorario))
				.uniqueResult();
	}
	
}
