package ar.edu.unlam.tallerweb1.controladores;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Fecha;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioEquipo;

@Controller
public class ControladorEquipo {

	@Inject
	private ServicioEquipo servicioEquipo;
	
	/*@RequestMapping("/registrar-equipo")
	public ModelAndView iniciarFecha() {

		ModelMap modelo = new ModelMap();
			
		Equipo equipo = new Equipo();
		modelo.put("equipo", equipo);
		modelo.put("torneos", servicioEquipo.getTorneosEnCurso());
		return new ModelAndView("registrar-equipo", modelo);*/
	}
}
