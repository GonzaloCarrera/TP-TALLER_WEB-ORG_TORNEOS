package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Fecha;
import ar.edu.unlam.tallerweb1.modelo.Torneo;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

@Repository("fechaDao")
public class FechaDaoImpl extends AbstractDao implements FechaDao {

	
	@Override
	public void guardarFecha(Fecha fecha) {
		getSession().saveOrUpdate(fecha);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Fecha> getFechasDeUnTorneo(Torneo torneo){
		return getSession().createCriteria(Fecha.class)
			.add(Restrictions.eq("torneo", torneo))
			.list();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public Integer getCantidadDeFechasDeUnTorneo(Torneo torneo){
		return getSession().createCriteria(Fecha.class)
			.add(Restrictions.eq("torneo", torneo))
			.list()
			.size();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public Fecha getFechaByIdFecha(Long idFecha){
		return (Fecha) getSession().createCriteria(Fecha.class)
			.add(Restrictions.eq("id", idFecha))
			.uniqueResult();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Fecha> getFechasDeUnTorneoByIdTorneo(Long idTorneo){
		return getSession().createCriteria(Fecha.class)
			.createAlias("torneo", "t")
			.add(Restrictions.eq("t.id", idTorneo))
			.list();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Fecha> getListaDeFechasEnCurso(){
		return getSession().createCriteria(Fecha.class)
			.add(Restrictions.eq("estado", "En curso"))
			.list();
	}

	@Override
	public Fecha getFechaEnPreparacionDeUnTorneo(Torneo torneo) {
		return (Fecha) getSession().createCriteria(Fecha.class)
				.add(Restrictions.eq("estado", "Preparacion"))
				.add(Restrictions.eq("torneo.id",torneo.getId()))
				.uniqueResult();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Fecha> getListaDeFechasEnPreparacion(){
		return getSession().createCriteria(Fecha.class)
			.add(Restrictions.eq("estado", "Preparacion"))
			.list();
	}
	
}
