package ar.edu.unlam.tallerweb1.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Equipo;
import ar.edu.unlam.tallerweb1.modelo.Fecha;
import ar.edu.unlam.tallerweb1.modelo.Horario;

@Repository("horarioDao")
public class HorarioDaoImpl extends AbstractDao implements HorarioDao {


	@Override
	public void guardarHorario(Horario horario) {
		getSession().saveOrUpdate(horario);
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
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Horario> getListaDeHorarioConSeleccionHorarioTrue() {
		List<Horario> listaDeHorarios = new ArrayList<Horario>();
		List<Horario> horarios = getSession().createCriteria(Horario.class)
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
		return (Horario) getSession().createCriteria(Horario.class)
				.add(Restrictions.eq("id", idHorario))
				.uniqueResult();
	}

	@Override
	public Horario getHorarioPorFechaYEquipo(Fecha fecha, Equipo equipo) {
		return (Horario) getSession().createCriteria(Horario.class)
				.add(Restrictions.eq("fecha", fecha))
				.add(Restrictions.eq("equipo", equipo))
				.uniqueResult();
	}
	
}
