package ar.edu.unlam.tallerweb1.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Equipo;

@Repository("equipoDao")
public class EquipoDaoImpl extends AbstractDao implements EquipoDao{
	
	@Override
	public void guardarEquipo(Equipo equipo) {
		getSession().saveOrUpdate(equipo);
	}
	
	@Override
	public Equipo getEquipoById(Long idEquipo) {
		return (Equipo) getSession().createCriteria(Equipo.class)
				.add(Restrictions.eq("id", idEquipo))
				.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Equipo> getListaDeEquiposByIdTorneo(Long idTorneo) {
		return getSession().createCriteria(Equipo.class)
				.createAlias("torneos", "t")
				.add(Restrictions.eq("t.id", idTorneo))
				.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Equipo> getListaDeEquiposCompleta(){
		List<Equipo> equipos = getSession().createCriteria(Equipo.class)
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
				if(!equiposDelUsuario.contains(e)){
					equiposDelUsuario.add(e);
				}
			}
		}
		return equiposDelUsuario;
	}

	
}
