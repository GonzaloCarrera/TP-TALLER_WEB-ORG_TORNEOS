package ar.edu.unlam.tallerweb1.controladores;

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
import ar.edu.unlam.tallerweb1.modelo.Torneo;
import ar.edu.unlam.tallerweb1.servicios.ServicioEquipo;
import ar.edu.unlam.tallerweb1.servicios.ServicioTorneo;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;

@Controller
public class ControladorTorneo {

	@Inject
	private ServicioTorneo servicioTorneo;
	
	@Inject
	private ServicioEquipo servicioEquipo;
	
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
	
	@RequestMapping("/torneos-activos")
	public ModelAndView torneosActivos() {

		ModelMap modelo = new ModelMap();
		modelo.put("torneos", servicioTorneo.getTorneosEnCurso());
		return new ModelAndView("torneos-activos", modelo);
	}


}
