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
	
	public List<Fecha> getFechasDeUnTorneo(Torneo torneo){
		final Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Fecha.class)
			.add(Restrictions.eq("torneo", torneo))
			.list();
	}
}
