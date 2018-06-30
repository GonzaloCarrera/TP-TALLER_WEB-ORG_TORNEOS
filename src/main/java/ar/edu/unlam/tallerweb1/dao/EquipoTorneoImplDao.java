package ar.edu.unlam.tallerweb1.dao;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Equipo;

@Repository("equipoTorneoDao")
public class EquipoTorneoImplDao implements EquipoTorneoDao {

	@Inject
    private SessionFactory sessionFactory;
	
}
