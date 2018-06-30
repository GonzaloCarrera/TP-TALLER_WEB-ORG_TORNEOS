package ar.edu.unlam.tallerweb1.dao;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Horario;
import ar.edu.unlam.tallerweb1.modelo.Torneo;

@Repository("horarioDao")
public class HorarioDaoImpl implements HorarioDao {

	@Inject
    private SessionFactory sessionFactory;

	@Override
	public void guardarHorario(Horario horario) {
		final Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(horario);
	}
}
