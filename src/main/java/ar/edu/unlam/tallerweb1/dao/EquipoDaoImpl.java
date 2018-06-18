package ar.edu.unlam.tallerweb1.dao;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Equipo;
import ar.edu.unlam.tallerweb1.modelo.Fecha;

@Repository("equipoDao")
public class EquipoDaoImpl implements EquipoDao{
	
	@Inject
    private SessionFactory sessionFactory;
	
	@Override
	public void guardarEquipo(Equipo equipo) {
		final Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(equipo);
	}
}
