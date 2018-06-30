package ar.edu.unlam.tallerweb1.servicios;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.EquipoDao;

@Service("servicioEquipoTorneo")
@Transactional
public class ServicioEquipoTorneoImpl implements ServicioEquipoTorneo{
	@Inject
	private EquipoDao equipoTorneoDao;

	
}
