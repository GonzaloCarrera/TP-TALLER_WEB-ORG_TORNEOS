package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Torneo;
import ar.edu.unlam.tallerweb1.modelo.Usuario;


@Repository("torneoDao")
public class TorneoDaoImpl implements TorneoDao{

	@Inject
    private SessionFactory sessionFactory;
	
	@Override
	public Torneo getTorneoById(Long idTorneo) {
		final Session session = sessionFactory.getCurrentSession();
		return (Torneo)session.createCriteria(Torneo.class)
				.add(Restrictions.eq("id", idTorneo))
				.uniqueResult();
	}
	
	@Override
	public void guardarTorneo(Torneo torneo) {
		final Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(torneo);
	}
	
	@Override
	public List<Torneo> getTorneosConInscripcionAbierta() {
		final Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Torneo.class)
				.add(Restrictions.eq("estado", "Inscripcion Abierta"))
				.list();
	}
	
	@Override
	public List<Torneo> getTorneosEnCurso(){
		final Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Torneo.class)
				.add(Restrictions.eq("estado", "En curso"))
				.list();
	}
	@Override
	public List<Torneo> getTorneosFinalizado() {
		final Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Torneo.class)
				.add(Restrictions.eq("estado", "Finalizado"))
				.list();
	}
}
