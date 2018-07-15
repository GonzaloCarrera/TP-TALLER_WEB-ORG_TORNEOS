package ar.edu.unlam.tallerweb1.controladores;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Equipo;
import ar.edu.unlam.tallerweb1.modelo.Estadistica;
import ar.edu.unlam.tallerweb1.modelo.Torneo;
import ar.edu.unlam.tallerweb1.servicios.ServicioEquipo;
import ar.edu.unlam.tallerweb1.servicios.ServicioFecha;
import ar.edu.unlam.tallerweb1.servicios.ServicioPartido;
import ar.edu.unlam.tallerweb1.servicios.ServicioTorneo;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;

@Controller
public class ControladorTorneo {

	@Inject
	private ServicioTorneo servicioTorneo;
	
	@Inject
	private ServicioEquipo servicioEquipo;
	
	@Inject
	private ServicioFecha servicioFecha;
	
	@Inject
	private ServicioPartido servicioPartido;
	
	@RequestMapping("/registrar-torneo")
	public ModelAndView registrarTorneo() {

		ModelMap modelo = new ModelMap();
		Torneo torneo = new Torneo();
		modelo.put("torneo", torneo);
		return new ModelAndView("registrar-torneo", modelo);
	}

	@RequestMapping(path = "/registrar-torneo", method = RequestMethod.POST)
	public ModelAndView registrarTorneoPost(@ModelAttribute("torneo") Torneo torneo, HttpServletRequest request) {
		ModelMap modelo = new ModelMap();	
		
		servicioTorneo.guardarTorneo(torneo);
		modelo.put("torneo", torneo);
		return new ModelAndView("torneo-creado", modelo);
	}
	
	@RequestMapping("/torneos-en-curso")
	public ModelAndView torneosActivos() {
		
		ModelMap modelo = new ModelMap();
		modelo.put("torneos", servicioTorneo.getTorneosEnCurso());
		return new ModelAndView("torneos-en-curso", modelo);
	}
	
	@RequestMapping("/listado-torneo-inscripcion-abierta")
	public ModelAndView listadoTorneosInscripcionAbierta(@RequestParam("idUsuario") Long idUsuario) {
		
		ModelMap modelo = new ModelMap();
		List<Integer> equiposIncriptos = new ArrayList<Integer>();
		List<Torneo> torneos = servicioTorneo.getTorneosConInscripcionAbierta();
		List<Integer> validarCantidadDeEquiposRegistradosEnElTorneo = new ArrayList<Integer>();
		for(Torneo t : torneos){
			equiposIncriptos.add(servicioEquipo.getListaDeEquiposByIdTorneo(t.getId()).size());
			validarCantidadDeEquiposRegistradosEnElTorneo.add(servicioEquipo.getCantidadDeEquiposRegistradorEnElTorneoPorElUsuario(t.getId(), idUsuario));
		}
		modelo.put("torneos", torneos);
		modelo.put("validarCantidadDeEquiposRegistradosEnElTorneo",validarCantidadDeEquiposRegistradosEnElTorneo);
		modelo.put("equiposIncriptos", equiposIncriptos);
		return new ModelAndView("listado-torneo-inscripcion-abierta", modelo);
	}
	
	@RequestMapping(path = "/seleccionar-equipo-torneo", method = RequestMethod.GET)
	public ModelAndView seleccionarEquipoTorneo(@RequestParam("idTorneo") Long idTorneo,
											  @RequestParam("idUsuario") Long idUsuario) {
		ModelMap modelo = new ModelMap();	
		List<Equipo> equipos = servicioEquipo.getListaDeEquiposByIdUsuario(idUsuario);
		modelo.put("idTorneo", idTorneo);
		modelo.put("equipos", equipos);
		return new ModelAndView("registrar-equipo-torneo", modelo);
	}
	
	@RequestMapping(path = "/registrar-equipo-torneo", method = RequestMethod.POST)
	public ModelAndView registrarEquipoTorneo(@RequestParam("idTorneo") Long idTorneo,
											  @RequestParam("idEquipo") Long idEquipo) {
		ModelMap modelo = new ModelMap();	
		Equipo equipo = servicioEquipo.getEquipoById(idEquipo);
		Torneo torneo = servicioTorneo.getTorneoById(idTorneo);
		equipo.getTorneos().add(torneo);
		servicioEquipo.guardarEquipo(equipo);
		if(servicioEquipo.getListaDeEquiposByIdTorneo(idTorneo).size()>=torneo.getCantidadDeEquipos()){
			torneo.setEstado("En curso");
			servicioTorneo.guardarTorneo(torneo);
		}
		modelo.put("equipo", equipo);
		modelo.put("torneo", torneo);
		return new ModelAndView("equipo-torneo-registrado", modelo);
	}
	
	
	@RequestMapping("/mis-torneos")
	public ModelAndView misTorneos(@RequestParam("idUsuario") Long idUsuario) {
		
		ModelMap modelo = new ModelMap();
		List<Torneo> torneos = new ArrayList<Torneo>();
		List<Equipo> equipos = servicioEquipo.getListaDeEquiposByIdUsuario(idUsuario);
		List<Integer> equiposIncriptos = new ArrayList<Integer>();
		for(Equipo e : equipos){
			for(Torneo t : e.getTorneos()){
				if(!torneos.contains(t)){
				torneos.add(t);
				equiposIncriptos.add(servicioEquipo.getListaDeEquiposByIdTorneo(t.getId()).size());
				}
			}
		}
		modelo.put("torneos", torneos);
		modelo.put("equiposIncriptos", equiposIncriptos);
		return new ModelAndView("mis-torneos", modelo);
	}
	
	@RequestMapping("/estadisticas")
	public ModelAndView estadisticas(@RequestParam("idTorneo") Long idTorneo) {
		
		ModelMap modelo = new ModelMap();
		Torneo torneo = servicioTorneo.getTorneoById(idTorneo);
		List<Estadistica> rank = servicioPartido.getTablaDePosicionesByTorneo(torneo);
		Collections.sort(rank);
		modelo.put("rank", rank);
		modelo.put("torneo", torneo);
		return new ModelAndView("estadisticas", modelo);
	}


}
