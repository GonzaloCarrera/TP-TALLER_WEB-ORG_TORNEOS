package ar.edu.unlam.tallerweb1.controladores;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import ar.edu.unlam.tallerweb1.modelo.Partido;
import ar.edu.unlam.tallerweb1.modelo.Torneo;
import ar.edu.unlam.tallerweb1.servicios.ServicioEquipo;
import ar.edu.unlam.tallerweb1.servicios.ServicioFecha;
import ar.edu.unlam.tallerweb1.servicios.ServicioHorario;
import ar.edu.unlam.tallerweb1.servicios.ServicioPartido;
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
	
	@Inject
	private ServicioPartido servicioPartido;
	
	@RequestMapping("/iniciar-fecha")
	public ModelAndView iniciarFecha() {

		ModelMap modelo = new ModelMap();
		modelo.put("torneos", servicioTorneo.getTorneosEnCurso());
		return new ModelAndView("iniciar-fecha", modelo);
	}
	
	@RequestMapping("/seleccionar-horario-fecha")
	public ModelAndView seleccionarHorarioFecha(@RequestParam("idTorneo") Long idTorneo) {

		ModelMap modelo = new ModelMap();
		if(servicioFecha.getCantidadDeFechasActivasDeUnTorneo(idTorneo)>0){
			modelo.put("error", "El torneo ya tiene una fecha activa.");
			modelo.put("torneos", servicioTorneo.getTorneosEnCurso());
			return new ModelAndView("iniciar-fecha", modelo);
		}
		modelo.put("idTorneo", idTorneo);
		return new ModelAndView("seleccionar-horario-fecha", modelo);
	}
	
	@RequestMapping(path= "/iniciar-fecha-torneo", method = RequestMethod.POST)
	public ModelAndView iniciarFechaPost(@RequestParam("idTorneo") Long idTorneo,
										 @RequestParam("horaInicio") String horaInicio,
										 @RequestParam("horaFin") String horaFin) {
		ModelMap modelo = new ModelMap();
		horaInicio = horaInicio.replace("T" , " ");
		horaFin = horaFin.replace("T" , " ");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		Fecha fecha = new Fecha();
		Torneo torneo = servicioTorneo.getTorneoById(idTorneo);
		List<Equipo> equipos = servicioEquipo.getListaDeEquiposByIdTorneo(idTorneo);
		fecha.setTorneo(torneo);
		try {
			fecha.setHoraInicio(sdf.parse(horaInicio));
			fecha.setHoraFin(sdf.parse(horaFin));
			servicioFecha.guardarFecha(fecha);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		for(Equipo e : equipos){
			Horario horario = new Horario();
			horario.setEquipo(e);
			horario.setFecha(fecha);
			servicioHorario.guardarHorario(horario);
		}
		if(servicioFecha.getFechasDeUnTorneoByIdTorneo(idTorneo).size()>=(torneo.getCantidadDeEquipos()-1)){
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
		List<Integer> fechaNumero = new ArrayList<Integer>();
		for(Fecha f : fechas){
			fechaNumero.add(servicioFecha.getCantidadDeFechasDeUnTorneo(f.getTorneo()));
		}
		modelo.put("fechas", fechas);
		modelo.put("fechaNumero", fechaNumero);
		return new ModelAndView("fechas-en-curso", modelo);
	}
	
	@RequestMapping(path= "/fechas-en-preparacion")
	public ModelAndView fechasEnPreparacion() {

		ModelMap modelo = new ModelMap();
		List<Fecha> fechas = servicioFecha.getListaDeFechasEnPreparacion();
		List<Integer> fechaNumero = new ArrayList<Integer>();
		List<Integer> cantidadDeEquiposQueSeleccionaronHorario = new ArrayList<Integer>();
		for(Fecha f : fechas){
			fechaNumero.add(servicioFecha.getCantidadDeFechasDeUnTorneo(f.getTorneo()));
			cantidadDeEquiposQueSeleccionaronHorario.add(servicioHorario.getCantidadDeEquiposQueSeleccionaronHorarioByIdFecha(f.getId()));
		}
		modelo.put("fechas", fechas);
		modelo.put("fechaNumero", fechaNumero);
		modelo.put("cantidadDeEquiposQueSeleccionaronHorario", cantidadDeEquiposQueSeleccionaronHorario);
		return new ModelAndView("fechas-en-preparacion", modelo);
	}
	
	@RequestMapping(path= "/machear-fecha")
	public ModelAndView machearFecha(@RequestParam("idTorneo") Long idTorneo) {

		ModelMap modelo = new ModelMap();
		Boolean fechaMacheada;
		try{
			fechaMacheada = servicioFecha.machearEquiposDelTorneoParaLaFechaEnPreparacion(idTorneo);
			if(fechaMacheada){
				Fecha fecha = servicioFecha.getFechaEnPreparacionDeUnTorneo(servicioTorneo.getTorneoById(idTorneo));
				fecha.setEstado("En curso");
				servicioFecha.guardarFecha(fecha);
			}
			modelo.put("torneo", servicioTorneo.getTorneoById(idTorneo));
		}
		catch(Exception e){
			modelo.put("error", "Ocurrio un error al intentar machear la fecha.");
		}
				
		return new ModelAndView("fecha-macheada", modelo);
	}
	
	
	@RequestMapping("/cargar-resultados")
	public ModelAndView cargarResultados(@RequestParam("idFecha") Long idFecha) {

		ModelMap modelo = new ModelMap();
		List<Partido> partidos = servicioPartido.getListaDePartidosDeLaFecha(idFecha);
		modelo.put("partidos", partidos);
		return new ModelAndView("cargar-resultados", modelo);
	}
	
	@RequestMapping(path="/cargar-resultados", method = RequestMethod.POST)
	public ModelAndView cargarResultadosPost(@RequestParam("idPartido") Long idPartido,
											 @RequestParam("golesEquipo1") Long golesEquipo1,
											 @RequestParam("golesEquipo2") Long golesEquipo2) {

		ModelMap modelo = new ModelMap();
		try{
			Partido partido = servicioPartido.getPartidoByIdPartido(idPartido);
			partido.setGolesEquipo1(golesEquipo1);
			partido.setGolesEquipo2(golesEquipo2);
			partido.setFinalizado(true);
			servicioPartido.guardarPartido(partido);
			modelo.put("exito", "Resultado cargado exitosamente.");
			List<Partido> partidos = servicioPartido.getListaDePartidosDeLaFecha(partido.getFecha().getId());
			modelo.put("partidos", partidos);
			if(servicioPartido.getListaDePartidosDeLaFecha(partido.getFecha().getId()).size()==0){
				partido.getFecha().setEstado("Finalizada");
				servicioFecha.guardarFecha(partido.getFecha());
			}
		}
		catch(Exception e){
			e.printStackTrace();
			modelo.put("error", "Ocurrio un error.");
		}
		return new ModelAndView("cargar-resultados", modelo);
	}
}
