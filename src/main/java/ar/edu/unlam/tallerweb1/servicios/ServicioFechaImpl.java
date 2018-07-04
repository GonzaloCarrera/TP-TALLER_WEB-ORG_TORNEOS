package ar.edu.unlam.tallerweb1.servicios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.FechaDao;
import ar.edu.unlam.tallerweb1.modelo.Equipo;
import ar.edu.unlam.tallerweb1.modelo.Fecha;
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
			//Ver que hacer aca... por que se puede quedear en un loop
			
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
				if(this.macheoDeHorarioDeLosEquipos(equipo,equipoDisponible) 
						&& !equiposDeLaFecha.contains(equipo) 
						&& !equiposDeLaFecha.contains(equipoDisponible)) {
					
					Partido partido = new Partido();
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

	private boolean macheoDeHorarioDeLosEquipos(Equipo equipo, Equipo equipoDisponible) {
		return true;
	}

	private List<Equipo> equiposQueJugaronConEquipo(Equipo equipo, List<Partido> partidos) {
		Set<Equipo> equipos = new HashSet<>();
		for (Partido partido : partidos) {
			if(partido.getEquipo1().equals(equipo) || partido.getEquipo2().equals(equipo)) {
				equipos.add(partido.getEquipo1());
				equipos.add(partido.getEquipo2());
			}
		}
		return new ArrayList<>(equipos);
	}
}
