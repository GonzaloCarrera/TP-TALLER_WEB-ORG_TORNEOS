package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Fecha;
import ar.edu.unlam.tallerweb1.modelo.Torneo;

@Repository("fechaDao")
public class FechaDaoImpl implements FechaDao {

	@Inject
    private SessionFactory sessionFactory;
	
	@Override
	public void guardarFecha(Fecha fecha) {
		final Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(fecha);
	}
	
	@Override
	public List<Fecha> getFechasDeUnTorneo(Torneo torneo){
		final Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Fecha.class)
			.add(Restrictions.eq("torneo", torneo))
			.list();
	}
	
	@Override
	public List<Fecha> getFechasDeUnTorneoByIdTorneo(Long idTorneo){
		final Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Fecha.class)
			.createAlias("torneo", "t")
			.add(Restrictions.eq("t.id", idTorneo))
			.list();
	}
	
	@Override
	public List<Fecha> getListaDeFechasEnCurso(){
		final Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Fecha.class)
			.add(Restrictions.eq("estado", "En curso"))
			.list();
	}
	
	@Override
	public List<Fecha> getListaDeFechasEnPreparacion(){
		final Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Fecha.class)
			.add(Restrictions.eq("estado", "Preparacion"))
			.list();
	}
}
