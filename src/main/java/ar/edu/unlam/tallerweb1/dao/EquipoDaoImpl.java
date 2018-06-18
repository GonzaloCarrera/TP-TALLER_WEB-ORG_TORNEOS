package ar.edu.unlam.tallerweb1.dao;

import javax.inject.Inject;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository("equipoDao")
public class EquipoDaoImpl implements EquipoDao{
	
	@Inject
    private SessionFactory sessionFactory;
}
