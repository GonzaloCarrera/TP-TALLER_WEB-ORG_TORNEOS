package ar.edu.unlam.tallerweb1.servicios;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.EquipoDao;
import ar.edu.unlam.tallerweb1.dao.FechaDao;

@Service("servicioEquipo")
@Transactional
public class ServicioEquipoImpl implements ServicioEquipo{

	@Inject
	private EquipoDao equipoDao;
}
