package ar.edu.unlam.tallerweb1.servicios;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

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

	private final Integer horarioInicio = 10;
	
	private final Integer horarioFin = 22;

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
			try {
			condicion = macheo(equipos, partidosDeLaFechaNueva, mapaDeEquiposDisponibles, fecha);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				return false;
			}
			
		}while(!condicion);
		
		for(Partido partido : partidosDeLaFechaNueva) {
			servicioPartido.guardarPartido(partido);
		}
		
		return true;
	}

	private Boolean macheo(List<Equipo> equipos, List<Partido> partidosDeLaFechaNueva,
		Map<Equipo, List<Equipo>> mapaDeEquiposDisponibles, Fecha fecha) throws Exception {
		Set<Equipo> equiposSinUsar = null;
		List<Partido> partidosDeLaFechaNuevaAux = null;
		do{
			Set<Equipo> equiposDeLaFecha = new HashSet<>();
			equiposSinUsar = new HashSet<>(equipos);
			partidosDeLaFechaNuevaAux = new ArrayList<>(partidosDeLaFechaNueva);
			for (Equipo equipo : mapaDeEquiposDisponibles.keySet()) {
				for(Equipo equipoDisponible :mapaDeEquiposDisponibles.get(equipo)) {
					Partido partido = new Partido();
					if(!equiposDeLaFecha.contains(equipo) && !equiposDeLaFecha.contains(equipoDisponible)
							&& this.macheoDeHorarioDeLosEquipos(equipo,equipoDisponible,fecha,partido) ) {
						
						partido.setEquipo1(equipo);
						partido.setEquipo2(equipoDisponible);
						partido.setFecha(fecha);
						equiposDeLaFecha.add(equipo);
						equiposDeLaFecha.add(equipoDisponible);
						partidosDeLaFechaNuevaAux.add(partido);
						equiposSinUsar.remove(equipo);
						equiposSinUsar.remove(equipoDisponible);
					}
				}
			}if(!equiposSinUsar.isEmpty()){
				for (Equipo equipo : mapaDeEquiposDisponibles.keySet()) {
					Collections.rotate(mapaDeEquiposDisponibles.get(equipo), 1);
				}
			}	
		}while(!equiposSinUsar.isEmpty());
//		if(!equiposSinUsar.isEmpty()) {
//			throw new Exception("Todos los equipos jugaron entre si");
//		}
		partidosDeLaFechaNueva.clear();
		partidosDeLaFechaNueva.addAll(partidosDeLaFechaNuevaAux);
		return equiposSinUsar.isEmpty();
	}

	private boolean macheoDeHorarioDeLosEquipos(Equipo equipo, Equipo equipoDisponible, Fecha fecha, Partido partido) {
		Horario horarioEquipo =servicioHorario.getHorarioPorFechaYEquipo(fecha, equipo);
		Horario horarioEquipoDisponible =servicioHorario.getHorarioPorFechaYEquipo(fecha, equipoDisponible);
		
		if(this.horarioPorEquipos(horarioEquipo,horarioEquipoDisponible,partido)){
			return true;
		}else if(this.horarioPorEquipos(horarioEquipoDisponible,horarioEquipo,partido)) {
			return true;
		}else {
				guardarHorarioEquipo(horarioEquipo);
				guardarHorarioEquipo(horarioEquipoDisponible);
			return this.horarioAutomatico(equipo,equipoDisponible,fecha,partido);
		}
	}

	private boolean horarioPorEquipos(Horario equipo1, Horario equipo2, Partido partido) {
		if(!(equipo1.getHoraInicio() == null || equipo2.getHoraInicio() == null || equipo1.getHoraFin() == null || equipo2.getHoraFin() == null)
				&&(equipo1.getHoraInicio().before(equipo2.getHoraInicio()) || equipo1.getHoraInicio().equals(equipo2.getHoraInicio()))
				&& equipo1.getHoraFin().after(equipo2.getHoraInicio())) {
			
			Integer diffHoras = (int) ((equipo1.getHoraFin().getTime() - equipo2.getHoraInicio().getTime())/3600000);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(equipo2.getHoraInicio());
			for(int i=0; i <= diffHoras; i++) {
				calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), calendar.get(Calendar.HOUR_OF_DAY)+1, 0);
				if(horarioValido(calendar.getTime())) {
					partido.setHorario(equipo2.getHoraInicio());
					guardarHorarioEquipo(equipo1);
					guardarHorarioEquipo(equipo2);
			return true;
			}
		}
	}
		return false;
	}

	private void guardarHorarioEquipo(Horario horarioEquipo) {
		horarioEquipo.setMacheado(true);
		horarioEquipo.setPermitirSeleccionHorario(false);
		servicioHorario.guardarHorario(horarioEquipo);
	}

	private Boolean horarioAutomatico(Equipo equipo, Equipo equipoDisponible, Fecha fecha, Partido partido) {
		
		Integer horaRand = 0;
		Integer diaRand = 0;
		Integer minutoRand = 0;
		Integer segundoRand = 0;
		Calendar calendar = Calendar.getInstance();
		Calendar inicio = Calendar.getInstance();
		Calendar fin = Calendar.getInstance();
		inicio.setTime(fecha.getHoraInicio());
		fin.setTime(fecha.getHoraFin());
		do {
			Integer diffDias = (int) ((fin.getTime().getTime() - inicio.getTime().getTime())/86400000); //milisegundos al dia
			if((inicio.get(Calendar.DATE) == fin.get(Calendar.DATE)) && (inicio.get(Calendar.MONTH) == fin.get(Calendar.MONTH))){
				do{
					horaRand = ThreadLocalRandom.current().nextInt(inicio.get(Calendar.HOUR_OF_DAY), fin.get(Calendar.HOUR_OF_DAY));
					minutoRand = (new Random().nextInt(12)+1) * 5; //intervalos de 5 min
					segundoRand = new Random().nextInt(60)+1;
					diaRand =  diffDias > 0 ?new Random().nextInt(diffDias): diffDias;
					calendar.setTime(fecha.getHoraInicio());
					calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE)+ diaRand, horaRand, minutoRand,segundoRand);
				}while(!(calendar.after(inicio) && calendar.before(fin)));
			}else{
				do{
					horaRand = ThreadLocalRandom.current().nextInt(horarioInicio, horarioFin);
					minutoRand = (new Random().nextInt(4)+1) * 15; //intervalos de 15 min
					segundoRand = new Random().nextInt(60)+1;
					diaRand =  diffDias > 0 ?new Random().nextInt(diffDias): diffDias;
					calendar.setTime(fecha.getHoraInicio());
					calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE)+ diaRand, horaRand, minutoRand,segundoRand);
				}while(!(calendar.after(inicio) && calendar.before(fin)));
			}
			
			
			
		}while( !(calendar.after(inicio)&&calendar.before(fin)) || !this.horarioValido(calendar.getTime()));
		
		partido.setHorario(calendar.getTime());
		return true;
	}

	private boolean horarioValido(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.HOUR_OF_DAY)>=horarioInicio && calendar.get(Calendar.HOUR_OF_DAY)<=horarioFin;
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
