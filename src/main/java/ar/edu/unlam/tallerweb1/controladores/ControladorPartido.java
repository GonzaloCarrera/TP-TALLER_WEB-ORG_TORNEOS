package ar.edu.unlam.tallerweb1.controladores;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Equipo;
import ar.edu.unlam.tallerweb1.modelo.Horario;
import ar.edu.unlam.tallerweb1.modelo.Partido;
import ar.edu.unlam.tallerweb1.modelo.Torneo;
import ar.edu.unlam.tallerweb1.servicios.ServicioEquipo;
import ar.edu.unlam.tallerweb1.servicios.ServicioFecha;
import ar.edu.unlam.tallerweb1.servicios.ServicioHorario;
import ar.edu.unlam.tallerweb1.servicios.ServicioPartido;
import ar.edu.unlam.tallerweb1.servicios.ServicioTorneo;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;

@Controller
public class ControladorPartido {

	@Inject
	private ServicioFecha servicioFecha;
	
	@Inject
	private ServicioEquipo servicioEquipo;
	
	@Inject
	private ServicioHorario servicioHorario;
	
	@Inject
	private ServicioUsuario servicioUsuario;
	
	@Inject
	private ServicioPartido servicioPartido;
	
	@RequestMapping("/proximos-partidos")
	public ModelAndView seleccionarHorario(@RequestParam("idUsuario") Long idUsuario) {

		ModelMap modelo = new ModelMap();
		List<Equipo> equipos = servicioEquipo.getListaDeEquiposByIdUsuario(idUsuario);
		List<Partido> partidos = servicioPartido.getListaDePartidosNoFinalizadosByListaDeEquipos(equipos);
		modelo.put("partidos", partidos);
		return new ModelAndView("proximos-partidos", modelo);
	}
}
