package ar.edu.unlam.tallerweb1.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Equipo;
import ar.edu.unlam.tallerweb1.modelo.Fecha;
import ar.edu.unlam.tallerweb1.modelo.Partido;
import ar.edu.unlam.tallerweb1.modelo.Torneo;
@Repository("partidoDao")
public class PartidoDaoImpl extends AbstractDao implements PartidoDao {

	@Override
	public void guardarPartido(Partido partido) {
		getSession().saveOrUpdate(partido);
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
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Partido> getListaDePartidosNoFinalizados() {
		List<Partido> listaDePartidos = new ArrayList<Partido>();
		List<Partido> partidos = getSession().createCriteria(Partido.class)
				.add(Restrictions.eq("finalizado", false))
				.list();
		for(Partido p : partidos){
			if(!listaDePartidos.contains(p)){
				listaDePartidos.add(p);
			}
		}
		return listaDePartidos;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Partido> getListaDePartidosDeLaFechaYTorneo(Fecha fecha, Torneo torneo) {
		return getSession().createCriteria(Partido.class)
				.add(Restrictions.eq("fecha", fecha))
				.add(Restrictions.eq("fecha.torneo", torneo))
				.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Partido> getListaDePartidosDelTorneo(Torneo torneo) {
		return getSession().createCriteria(Partido.class)
				.createAlias("fecha", "f")
				.add(Restrictions.eq("f.torneo", torneo))
				.list();
	}
	
}
