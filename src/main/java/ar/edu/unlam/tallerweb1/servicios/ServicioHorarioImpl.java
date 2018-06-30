package ar.edu.unlam.tallerweb1.servicios;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.HorarioDao;
import ar.edu.unlam.tallerweb1.modelo.Horario;

@Service("servicioHorario")
@Transactional
public class ServicioHorarioImpl implements ServicioHorario {

	@Inject
	private HorarioDao horarioDao;

	@Override
	public void guardarHorario(Horario horario) {
		horarioDao.guardarHorario(horario);		
	}
	
	
}
