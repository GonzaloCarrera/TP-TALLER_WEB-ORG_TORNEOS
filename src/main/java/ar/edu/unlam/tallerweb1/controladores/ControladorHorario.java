package ar.edu.unlam.tallerweb1.controladores;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Equipo;
import ar.edu.unlam.tallerweb1.modelo.Horario;
import ar.edu.unlam.tallerweb1.servicios.ServicioEquipo;
import ar.edu.unlam.tallerweb1.servicios.ServicioFecha;
import ar.edu.unlam.tallerweb1.servicios.ServicioHorario;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;

@Controller
public class ControladorHorario {

	@Inject
	private ServicioFecha servicioFecha;
	
	@Inject
	private ServicioEquipo servicioEquipo;
	
	@Inject
	private ServicioHorario servicioHorario;
	
	@Inject
	private ServicioUsuario servicioUsuario;
	
	@RequestMapping("/seleccionar-horario")
	public ModelAndView seleccionarHorario(@RequestParam("idUsuario") Long idUsuario) {

		ModelMap modelo = new ModelMap();
		List<Equipo> equipos = servicioEquipo.getListaDeEquiposByIdUsuario(idUsuario);
		List<Horario> horarios = servicioHorario.getListaDeHorariosPermitirSeleccionTrueByIdEquipo(equipos);
		modelo.put("horarios", horarios);
		return new ModelAndView("seleccionar-horario", modelo);
	}
	
	@RequestMapping("/seleccionar-horario-partido")
	public ModelAndView seleccionarHorarioPartido(@RequestParam("idHorario") Long idHorario) {

		ModelMap modelo = new ModelMap();
		Horario horario = servicioHorario.getHorarioByIdHorario(idHorario);
		modelo.put("horario", horario);
		return new ModelAndView("seleccionar-horario-partido", modelo);
	}
	
	@RequestMapping(path="/seleccionar-horario-partido", method = RequestMethod.POST)
	public ModelAndView seleccionarHorarioPartidoPost(@RequestParam("idHorario") Long idHorario,
												      @RequestParam("horaInicio") String horaInicio,
													  @RequestParam("horaFin") String horaFin) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		ModelMap modelo = new ModelMap();
		Horario horario = servicioHorario.getHorarioByIdHorario(idHorario);
		horario.setPermitirSeleccionHorario(false);
		try {
			horario.setHoraInicio(sdf.parse(horaInicio));
			horario.setHoraFin(sdf.parse(horaFin));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		servicioHorario.guardarHorario(horario);
		modelo.put("horario", horario);
		return new ModelAndView("horario-registrado", modelo);
	}
}
