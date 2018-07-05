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

import ar.edu.unlam.tallerweb1.dao.FechaDao;
import ar.edu.unlam.tallerweb1.modelo.Equipo;
import ar.edu.unlam.tallerweb1.modelo.Fecha;
import ar.edu.unlam.tallerweb1.modelo.Horario;
import ar.edu.unlam.tallerweb1.modelo.Torneo;
import ar.edu.unlam.tallerweb1.servicios.ServicioEquipo;
import ar.edu.unlam.tallerweb1.servicios.ServicioFecha;
import ar.edu.unlam.tallerweb1.servicios.ServicioHorario;
import ar.edu.unlam.tallerweb1.servicios.ServicioTorneo;

@Controller
public class ControladorFecha {

	@Inject
	private ServicioTorneo servicioTorneo;
	
	@Inject
	private ServicioFecha servicioFecha;
	
	@Inject
	private ServicioEquipo servicioEquipo;
	
	@Inject
	private ServicioHorario servicioHorario;
	
	@RequestMapping("/iniciar-fecha")
	public ModelAndView iniciarFecha() {

		ModelMap modelo = new ModelMap();
		modelo.put("torneos", servicioTorneo.getTorneosEnCurso());
		return new ModelAndView("iniciar-fecha", modelo);
	}
	
	@RequestMapping(path= "/iniciar-fecha-torneo")
	public ModelAndView iniciarFechaPost(@RequestParam("idTorneo") Long idTorneo) {
		ModelMap modelo = new ModelMap();
		if(servicioFecha.getCantidadDeFechasActivasDeUnTorneo(idTorneo)>0){
			modelo.put("error", "El torneo ya tiene una fecha activa.");
			modelo.put("torneos", servicioTorneo.getTorneosEnCurso());
			return new ModelAndView("iniciar-fecha", modelo);
		}
		Fecha fecha = new Fecha();
		Torneo torneo = servicioTorneo.getTorneoById(idTorneo);
		List<Equipo> equipos = servicioEquipo.getListaDeEquiposByIdTorneo(idTorneo);
		fecha.setTorneo(torneo);
		servicioFecha.guardarFecha(fecha);
		for(Equipo e : equipos){
			Horario horario = new Horario();
			horario.setEquipo(e);
			horario.setFecha(fecha);
			servicioHorario.guardarHorario(horario);
		}
		if(servicioFecha.getCantidadDeFechasDeUnTorneo(torneo)>=(torneo.getCantidadDeEquipos()-1)){
			torneo.setEstado("Finalizado");
			servicioTorneo.guardarTorneo(torneo);
		}
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
	
	@RequestMapping(path= "/fechas-en-curso")
	public ModelAndView fechasEnCurso() {

		ModelMap modelo = new ModelMap();
		List<Fecha> fechas = servicioFecha.getListaDeFechasEnCurso();
		modelo.put("fechas", fechas);
		return new ModelAndView("fechas-en-curso", modelo);
	}
	
	@RequestMapping(path= "/fechas-en-preparacion")
	public ModelAndView fechasEnPreparacion() {

		ModelMap modelo = new ModelMap();
		List<Fecha> fechas = servicioFecha.getListaDeFechasEnPreparacion();
		modelo.put("fechas", fechas);
		return new ModelAndView("fechas-en-preparacion", modelo);
	}
	
	@RequestMapping(path= "/machear-fecha")
	public ModelAndView machearFecha(@RequestParam("idTorneo") Long idTorneo) {

		ModelMap modelo = new ModelMap();
		Boolean bool = servicioFecha.machearEquiposDelTorneoParaLaFechaEnPreparacion(idTorneo);
		
		return new ModelAndView("fecha-macheada", modelo);
	}
}
