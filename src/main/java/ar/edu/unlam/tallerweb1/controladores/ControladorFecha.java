package ar.edu.unlam.tallerweb1.controladores;

import java.util.List;

import javax.inject.Inject;

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
import ar.edu.unlam.tallerweb1.servicios.ServicioFecha;
import ar.edu.unlam.tallerweb1.servicios.ServicioTorneo;

@Controller
public class ControladorFecha {

	@Inject
	private ServicioTorneo servicioTorneo;
	
	@Inject
	private ServicioFecha servicioFecha;
	
	@RequestMapping("/iniciar-fecha")
	public ModelAndView iniciarFecha() {

		ModelMap modelo = new ModelMap();
		Fecha fecha = new Fecha();
		modelo.put("fecha", fecha);
		modelo.put("torneos", servicioTorneo.getTorneosEnCurso());
		return new ModelAndView("iniciar-fecha", modelo);
	}
	
	@RequestMapping(path= "/iniciar-fecha", method = RequestMethod.POST)
	public ModelAndView iniciarFechaPost(@ModelAttribute("fecha") Fecha fecha, @ModelAttribute("idTorneo") Long idTorneo) {

		ModelMap modelo = new ModelMap(); //faltan agregar validaciones, ej: si el torneo ya tiene una fecha en curso no podria iniciarse otra fecha.
		Torneo torneo = servicioTorneo.getTorneoById(idTorneo);
		fecha.setTorneo(torneo);
		servicioFecha.guardarFecha(fecha);
		modelo.put("torneo", torneo);
		return new ModelAndView("fecha-creada", modelo);
	}
	
	@RequestMapping(path = "/listado-fechas-torneo", method = RequestMethod.POST)
	public ModelAndView listadoFechasTorneo(@RequestParam("idTorneo") Long idTorneo) {
		ModelMap modelo = new ModelMap();	
		
		List<Fecha> fechas = servicioFecha.getFechasDeUnTorneoByIdTorneo(idTorneo);
		modelo.put("fechas", fechas);
		return new ModelAndView("listado-fechas-torneo", modelo);
	}
	
}
