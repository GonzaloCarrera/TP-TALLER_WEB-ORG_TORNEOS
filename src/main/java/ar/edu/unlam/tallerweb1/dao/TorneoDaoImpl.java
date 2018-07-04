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
public class TorneoDaoImpl extends AbstractDao implements TorneoDao{

	
	@Override
	public Torneo getTorneoById(Long idTorneo) {
		return (Torneo)getSession().createCriteria(Torneo.class)
				.add(Restrictions.eq("id", idTorneo))
				.uniqueResult();
	}
	
	@Override
	public void guardarTorneo(Torneo torneo) {
		final Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(torneo);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Torneo> getTorneosConInscripcionAbierta() {
		return getSession().createCriteria(Torneo.class)
				.add(Restrictions.eq("estado", "Inscripcion Abierta"))
				.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Torneo> getTorneosEnCurso(){
		return getSession().createCriteria(Torneo.class)
				.add(Restrictions.eq("estado", "En curso"))
				.list();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Torneo> getTorneosFinalizado() {
		return getSession().createCriteria(Torneo.class)
				.add(Restrictions.eq("estado", "Finalizado"))
				.list();
	}
	
}
