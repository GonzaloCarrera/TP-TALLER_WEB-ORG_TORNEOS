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
import ar.edu.unlam.tallerweb1.servicios.ServicioJugador;
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
	
	@Inject
	private ServicioJugador servicioJugador;
	
	@RequestMapping("/registrar-equipo")
	public ModelAndView registrarEquipo() {
		
		ModelMap modelo = new ModelMap();
		return new ModelAndView("registrar-equipo", modelo);
	}
	
	@RequestMapping(path = "/registrar-equipo", method = RequestMethod.POST)
	public ModelAndView registrarEquipoPost(@RequestParam("nombreEquipo") String nombreEquipo,
											@RequestParam("idUsuario") Long idUsuario,
											@RequestParam("nombreJugador1") String nombreJugador1,
											@RequestParam("nombreJugador2") String nombreJugador2,
											@RequestParam("nombreJugador3") String nombreJugador3,
											@RequestParam("nombreJugador4") String nombreJugador4,
											@RequestParam("nombreJugador5") String nombreJugador5,
											@RequestParam("nombreJugador6") String nombreJugador6,
											@RequestParam("nombreJugador7") String nombreJugador7,
											@RequestParam("nombreJugador8") String nombreJugador8,
											@RequestParam("nombreJugador9") String nombreJugador9,
											@RequestParam("nombreJugador10") String nombreJugador10) {
		ModelMap modelo = new ModelMap();
	
		Equipo equipo = new Equipo();
		equipo.setNombreEquipo(nombreEquipo);
		equipo.setUsuario(servicioUsuario.getUsuarioById(idUsuario));
		servicioEquipo.guardarEquipo(equipo);
		Jugador jugador1 = new Jugador();
		Jugador jugador2 = new Jugador();
		Jugador jugador3 = new Jugador();
		Jugador jugador4 = new Jugador();
		Jugador jugador5 = new Jugador();
		Jugador jugador6 = new Jugador();
		Jugador jugador7 = new Jugador();
		Jugador jugador8 = new Jugador();
		Jugador jugador9 = new Jugador();
		Jugador jugador10 = new Jugador();
		jugador1.setNombre(nombreJugador1);
		jugador1.setEquipo(equipo);
		servicioJugador.guardarJugador(jugador1);
		jugador2.setNombre(nombreJugador2);
		jugador2.setEquipo(equipo);
		servicioJugador.guardarJugador(jugador2);
		jugador3.setNombre(nombreJugador3);
		jugador3.setEquipo(equipo);
		servicioJugador.guardarJugador(jugador3);
		jugador4.setNombre(nombreJugador4);
		jugador4.setEquipo(equipo);
		servicioJugador.guardarJugador(jugador4);
		jugador5.setNombre(nombreJugador5);
		jugador5.setEquipo(equipo);
		servicioJugador.guardarJugador(jugador5);
		jugador6.setNombre(nombreJugador6);
		jugador6.setEquipo(equipo);
		servicioJugador.guardarJugador(jugador6);
		jugador7.setNombre(nombreJugador7);
		jugador7.setEquipo(equipo);
		servicioJugador.guardarJugador(jugador7);
		jugador8.setNombre(nombreJugador8);
		jugador8.setEquipo(equipo);
		servicioJugador.guardarJugador(jugador8);
		jugador9.setNombre(nombreJugador9);
		jugador9.setEquipo(equipo);
		servicioJugador.guardarJugador(jugador9);
		jugador10.setNombre(nombreJugador10);
		jugador10.setEquipo(equipo);
		servicioJugador.guardarJugador(jugador10);
		modelo.put("equipo", equipo);
		return new ModelAndView("equipo-creado", modelo);
	}
}
