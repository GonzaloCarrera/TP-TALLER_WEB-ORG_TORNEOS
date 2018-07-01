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
import ar.edu.unlam.tallerweb1.modelo.Torneo;

@Repository("equipoDao")
public class EquipoDaoImpl implements EquipoDao{
	
	@Inject
    private SessionFactory sessionFactory;
	
	@Override
	public void guardarEquipo(Equipo equipo) {
		final Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(equipo);
	}
	
	@Override
	public Equipo getEquipoById(Long idEquipo) {
		final Session session = sessionFactory.getCurrentSession();
		return (Equipo) session.createCriteria(Equipo.class)
				.add(Restrictions.eq("id", idEquipo))
				.uniqueResult();
	}
	
	@Override
	public List<Equipo> getListaDeEquiposByIdTorneo(Long idTorneo) {
		/*List<Equipo> listaDeequipos = this.getListaDeEquiposCompleta();
		List<Equipo> equipos = new ArrayList<Equipo>();
		for(Equipo e : listaDeequipos){
			for(Torneo t : e.getTorneos()){
				if(t.getId()==idTorneo){
					equipos.add(e);
				}
			}
		}
		return equipos;*/
	final Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Equipo.class)
				.createAlias("torneos", "t")
				.add(Restrictions.eq("t.id", idTorneo))
				.list();
	}
	
	public List<Equipo> getListaDeEquiposCompleta(){
		final Session session = sessionFactory.getCurrentSession();
		List<Equipo> equipos = session.createCriteria(Equipo.class)
				.list();
		List<Equipo> listaDeEquipos = new ArrayList<Equipo>();
		for(Equipo e : equipos){
			if(!listaDeEquipos.contains(e)){
				listaDeEquipos.add(e);
			}
		}
		return listaDeEquipos;
	}
	
	@Override
	public List<Equipo> getListaDeEquiposByIdUsuario(Long idUsuario) {
		List<Equipo> equipos = this.getListaDeEquiposCompleta();
		List<Equipo> equiposDelUsuario= new ArrayList<Equipo>();
		for(Equipo e : equipos){
			if(e.getUsuario().getId()==idUsuario){
					equiposDelUsuario.add(e);
			}
		}
		return equiposDelUsuario;
		/*final Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Equipo.class)
				.createAlias("usuario", "u")
				.add(Restrictions.eq("u.id", idUsuario))
				.list();*/
	}//ERROR, TRAE VALORES REPETIDOS
}
