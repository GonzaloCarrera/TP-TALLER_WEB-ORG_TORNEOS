package ar.edu.unlam.tallerweb1.controladores;

import java.util.ArrayList;
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
import ar.edu.unlam.tallerweb1.modelo.Fecha;
import ar.edu.unlam.tallerweb1.modelo.Torneo;
import ar.edu.unlam.tallerweb1.servicios.ServicioEquipo;
import ar.edu.unlam.tallerweb1.servicios.ServicioFecha;
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
	public ModelAndView listadoTorneosInscripcionAbierta() {
		
		ModelMap modelo = new ModelMap();
		modelo.put("torneos", servicioTorneo.getTorneosConInscripcionAbierta());
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
		for(Equipo e : equipos){
			for(Torneo t : e.getTorneos()){
				torneos.add(t);
			}
		}
		modelo.put("torneos", torneos);
		return new ModelAndView("mis-torneos", modelo);
	}


}
