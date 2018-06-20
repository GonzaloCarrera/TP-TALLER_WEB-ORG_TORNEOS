package ar.edu.unlam.tallerweb1.dao;

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
	public List<Equipo> getListaDeEquiposByIdTorneo(Long idTorneo) {
		final Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Equipo.class)
				.createAlias("torneo", "t")
				.add(Restrictions.eq("t.id", idTorneo))
				.list();
	}
}
