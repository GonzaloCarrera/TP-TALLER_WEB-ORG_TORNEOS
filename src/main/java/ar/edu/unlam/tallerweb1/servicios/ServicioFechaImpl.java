package ar.edu.unlam.tallerweb1.servicios;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.FechaDao;
import ar.edu.unlam.tallerweb1.modelo.Equipo;
import ar.edu.unlam.tallerweb1.modelo.Fecha;
import ar.edu.unlam.tallerweb1.modelo.Horario;
import ar.edu.unlam.tallerweb1.modelo.Partido;
import ar.edu.unlam.tallerweb1.modelo.Torneo;

@Service("servicioFecha")
@Transactional
public class ServicioFechaImpl implements ServicioFecha{

	@Inject
	private FechaDao fechaDao;
	
	@Autowired
	private ServicioEquipo servicioEquipo;
	
	@Autowired
	private ServicioTorneo servicioTorneo;
	
	@Autowired
	private ServicioPartido servicioPartido;
	
	@Autowired
	private ServicioHorario servicioHorario;

	@Override
	public void guardarFecha(Fecha fecha) {
		fechaDao.guardarFecha(fecha);
	}

	@Override
	public List<Fecha> getFechasDeUnTorneo(Torneo torneo) {
		return fechaDao.getFechasDeUnTorneo(torneo);
	}

	@Override
	public List<Fecha> getFechasDeUnTorneoByIdTorneo(Long idTorneo) {
		return fechaDao.getFechasDeUnTorneoByIdTorneo(idTorneo);
	}

	@Override
	public List<Fecha> getListaDeFechasEnCurso() {
		return fechaDao.getListaDeFechasEnCurso();
	}

	@Override
	public List<Fecha> getListaDeFechasEnPreparacion() {
		return fechaDao.getListaDeFechasEnPreparacion();
	}
		
	public Fecha getFechaEnPreparacionDeUnTorneo(Torneo torneo) {
		return fechaDao.getFechaEnPreparacionDeUnTorneo(torneo);
	}

	@Override
	public Boolean machearEquiposDelTorneoParaLaFechaEnPreparacion(Long idTorneo) {
		
		List<Equipo> equipos =  servicioEquipo.getListaDeEquiposByIdTorneo(idTorneo);
		Torneo torneo = servicioTorneo.getTorneoById(idTorneo);
		List<Partido> partidosDeLaFechaNueva = new ArrayList<>();
		Map<Equipo,List<Equipo>> mapaDeEquiposDisponibles = new HashMap<Equipo,List<Equipo>>();
		List<Partido> partidos = servicioPartido.getListaDePartidosDelTorneo(torneo);
		for (Equipo equipo : equipos) {
			List<Equipo> equiposAux = new ArrayList<>(equipos);
			List<Equipo> equiposASacar = this.equiposQueJugaronConEquipo(equipo, partidos);
			equiposAux.removeAll(equiposASacar);
			mapaDeEquiposDisponibles.put(equipo, equiposAux);
		}
		
		Fecha fecha = this.getFechaEnPreparacionDeUnTorneo(torneo);
		Boolean condicion = false;
		do{
			condicion = macheo(equipos, partidosDeLaFechaNueva, mapaDeEquiposDisponibles, fecha);
		}while(!condicion);
		
		for(Partido partido : partidosDeLaFechaNueva) {
			servicioPartido.guardarPartido(partido);
		}
		
		return true;
	}

	private Boolean macheo(List<Equipo> equipos, List<Partido> partidosDeLaFechaNueva,
		Map<Equipo, List<Equipo>> mapaDeEquiposDisponibles, Fecha fecha) {
		Set<Equipo> equiposDeLaFecha = new HashSet<>();
		Set<Equipo> equiposSinUsar = new HashSet<>(equipos);
		for (Equipo equipo : mapaDeEquiposDisponibles.keySet()) {
			for(Equipo equipoDisponible :mapaDeEquiposDisponibles.get(equipo)) {
				Partido partido = new Partido();
				if(this.macheoDeHorarioDeLosEquipos(equipo,equipoDisponible,fecha,partido) 
						&& !equiposDeLaFecha.contains(equipo) 
						&& !equiposDeLaFecha.contains(equipoDisponible)) {
					
					partido.setEquipo1(equipo);
					partido.setEquipo2(equipoDisponible);
					partido.setFecha(fecha);
					equiposDeLaFecha.add(equipo);
					equiposDeLaFecha.add(equipoDisponible);
					partidosDeLaFechaNueva.add(partido);
					equiposSinUsar.remove(equipo);
					equiposSinUsar.remove(equipoDisponible);
				}
			}
		}
		return equiposSinUsar.isEmpty();
	}

	private boolean macheoDeHorarioDeLosEquipos(Equipo equipo, Equipo equipoDisponible, Fecha fecha, Partido parido) {
		Horario horarioEquipo =servicioHorario.getHorarioPorFechaYEquipo(fecha, equipo);
		Horario horarioEquipoDisponible =servicioHorario.getHorarioPorFechaYEquipo(fecha, equipoDisponible);
		
		if(horarioEquipo.getHoraInicio() == null || horarioEquipoDisponible.getHoraInicio() == null || horarioEquipo.getHoraFin() == null || horarioEquipoDisponible.getHoraFin() == null) {
			return this.horarioAutomatico(equipo,equipoDisponible,fecha,parido,horarioEquipo,horarioEquipoDisponible);
		}else {
			
		if(horarioEquipo.getHoraInicio().before(horarioEquipoDisponible.getHoraInicio()) && horarioEquipo.getHoraFin().after(horarioEquipoDisponible.getHoraInicio())){
			parido.setHorario(horarioEquipoDisponible.getHoraInicio());
				guardarHorarioEquipo(horarioEquipo);
				guardarHorarioEquipo(horarioEquipoDisponible);
			return true;
		}else if(horarioEquipoDisponible.getHoraInicio().before(horarioEquipo.getHoraInicio()) && horarioEquipoDisponible.getHoraFin().after(horarioEquipo.getHoraInicio())) {
			parido.setHorario(horarioEquipo.getHoraInicio());
				guardarHorarioEquipo(horarioEquipo);
				guardarHorarioEquipo(horarioEquipoDisponible);
			return true;
			}else {
				return this.horarioAutomatico(equipo,equipoDisponible,fecha,parido,horarioEquipo,horarioEquipoDisponible);
			}
		}
	}

	private void guardarHorarioEquipo(Horario horarioEquipo) {
		horarioEquipo.setMacheado(true);
		horarioEquipo.setPermitirSeleccionHorario(false);
		servicioHorario.guardarHorario(horarioEquipo);
	}

	private Boolean horarioAutomatico(Equipo equipo, Equipo equipoDisponible, Fecha fecha, Partido partido, Horario horarioEquipo, Horario horarioEquipoDisponible) {
		Integer horaRand = 0;
		Integer diaRand = 0;
		Calendar calendar = Calendar.getInstance();
		while(horaRand<10) {
			horaRand = new Random().nextInt(16)+1;
			diaRand =  new Random().nextInt(3);
			Integer hora = (int) (horaRand * 1.5f);
			calendar.setTime(fecha.getHoraInicio());
			calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE)+ diaRand, hora, 0);
			if(calendar.getTime().before(fecha.getHoraInicio()) || calendar.getTime().after(fecha.getHoraFin()) || hora < 14) {
				horaRand =0;
		}
		}
		
		partido.setHorario(calendar.getTime());
		guardarHorarioEquipo(horarioEquipo);
		guardarHorarioEquipo(horarioEquipoDisponible);
		return true;
	}

	private List<Equipo> equiposQueJugaronConEquipo(Equipo equipo, List<Partido> partidos) {
		Set<Equipo> equipos = new HashSet<>();
		equipos.add(equipo);
		for (Partido partido : partidos) {
			if(partido.getEquipo1().equals(equipo) || partido.getEquipo2().equals(equipo)) {
				equipos.add(partido.getEquipo1());
				equipos.add(partido.getEquipo2());
			}
		}
		return new ArrayList<>(equipos);
	}

	@Override
	public Integer getCantidadDeFechasActivasDeUnTorneo(Long idTorneo) {
			Integer contador = 0;
			List<Fecha> fechas = fechaDao.getFechasDeUnTorneoByIdTorneo(idTorneo);
			for(Fecha f : fechas){
				if(f.getEstado().equals("En curso")||f.getEstado().equals("Preparacion")){
					contador++;
				}
			}
			return contador;
		}

	@Override
	public Integer getCantidadDeFechasDeUnTorneo(Torneo torneo) {
		return fechaDao.getCantidadDeFechasDeUnTorneo(torneo);
	}

	@Override
	public Fecha getFechaByIdFecha(Long idFecha) {
		return fechaDao.getFechaByIdFecha(idFecha);
	}

	@Override
	public List<Fecha> getFechasEnCursoOFinalizadasDeUnTorneoByIdTorneo(Long idTorneo) {
		return fechaDao.getFechasEnCursoOFinalizadasDeUnTorneoByIdTorneo(idTorneo);
	}
	
	
}
