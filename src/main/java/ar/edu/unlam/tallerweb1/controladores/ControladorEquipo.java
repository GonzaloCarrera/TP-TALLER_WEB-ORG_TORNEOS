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
import ar.edu.unlam.tallerweb1.modelo.Jugador;
import ar.edu.unlam.tallerweb1.modelo.Torneo;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioEquipo;
import ar.edu.unlam.tallerweb1.servicios.ServicioTorneo;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;

@Controller
public class ControladorEquipo {

	@Inject
	private ServicioEquipo servicioEquipo;
	
	@Inject
	private ServicioTorneo servicioTorneo;
	
	@Inject
	private ServicioUsuario servicioUsuario;
	
	@RequestMapping("/registrar-equipo")
	public ModelAndView iniciarFecha() {

		ModelMap modelo = new ModelMap();
		List<Jugador> jugadores = new ArrayList<Jugador>(10);
		Equipo equipo = new Equipo();
		modelo.put("jugadores", jugadores);
		modelo.put("equipo", equipo);
		//modelo.put("torneos", servicioTorneo.getTorneosEnCurso());
		return new ModelAndView("registrar-equipo", modelo);
	}
	
	@RequestMapping(path = "/registrar-equipo", method = RequestMethod.POST)
	public ModelAndView registrarTorneoPost(@ModelAttribute("equipo") Equipo equipo) {
		ModelMap modelo = new ModelMap();
		
		/*Torneo torneo = servicioTorneo.getTorneoById(equipo.getTorneo().getId());
		Usuario usuario = servicioUsuario.getUsuarioById(equipo.getUsuario().getId());
		equipo.setTorneo(torneo);
		equipo.setUsuario(usuario);*/
		servicioEquipo.guardarEquipo(equipo);
		return new ModelAndView("equipo-creado", modelo);
	}
}
