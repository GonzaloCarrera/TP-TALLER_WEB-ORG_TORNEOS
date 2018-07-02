package ar.edu.unlam.tallerweb1.dao;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Equipo;
import ar.edu.unlam.tallerweb1.modelo.Horario;
import ar.edu.unlam.tallerweb1.modelo.Partido;
@Repository("partidoDao")
public class PartidoDaoImpl implements PartidoDao {

	@Inject
    private SessionFactory sessionFactory;
	
	@Override
	public void guardarPartido(Partido partido) {
		final Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(partido);
	}
	
	@Override
	public List<Partido> getListaDePartidosNoFinalizadosByListaDeEquipos(List<Equipo> equipos) {
		List<Partido> partidos = this.getListaDePartidosNoFinalizados();
		List<Partido> listaDePartidosNoFinalizadosDelUsuario = new ArrayList<Partido>();
		for(Partido p : partidos){
			for(Equipo e : equipos){
				if(p.getEquipo1().getId()==e.getId()||p.getEquipo2().getId()==e.getId()){
					listaDePartidosNoFinalizadosDelUsuario.add(p);
				}
			}
		}
		return listaDePartidosNoFinalizadosDelUsuario;
	}
	
	@Override
	public List<Partido> getListaDePartidosNoFinalizados() {
		final Session session = sessionFactory.getCurrentSession();
		List<Partido> listaDePartidos = new ArrayList<Partido>();
		List<Partido> partidos = session.createCriteria(Partido.class)
				.add(Restrictions.eq("finalizado", false))
				.list();
		for(Partido p : partidos){
			if(!listaDePartidos.contains(p)){
				listaDePartidos.add(p);
			}
		}
		return listaDePartidos;
	}
	
}
