package ar.edu.unlam.tallerweb1.controladores;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Equipo;
import ar.edu.unlam.tallerweb1.modelo.Partido;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioEquipo;
import ar.edu.unlam.tallerweb1.servicios.ServicioFecha;
import ar.edu.unlam.tallerweb1.servicios.ServicioPartido;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;

@Controller
public class ControladorLogin {

	@Inject
	private ServicioUsuario servicioUsuario;
	
	@Inject
	private ServicioFecha servicioFecha;

	@Inject
	private ServicioEquipo servicioEquipo;
	
	@Inject
	private ServicioPartido servicioPartido;

	@PostConstruct
	public void init() {

	}

	@RequestMapping("/login")
	public ModelAndView irALogin() {

		ModelMap modelo = new ModelMap();
		Usuario usuario = new Usuario();
		modelo.put("usuario", usuario);
		return new ModelAndView("login", modelo);
	}

	@RequestMapping(path = "/validar-login", method = RequestMethod.POST)
	public ModelAndView validarLogin(@ModelAttribute("usuario") Usuario usuario, HttpServletRequest request) {
		ModelMap model = new ModelMap();
		Usuario usuarioBuscado = servicioUsuario.consultarUsuario(usuario);
		if (usuarioBuscado != null) {
			request.getSession().setAttribute("user", usuarioBuscado);
			return new ModelAndView("redirect:/home");
		} else {
			model.put("error", "Usuario o clave incorrecta");
		}
		return new ModelAndView("login", model);
	}

	@RequestMapping(path = "/home", method = RequestMethod.GET)
	public ModelAndView irAHome(HttpServletRequest request) {
		ModelMap modelo = new ModelMap();
		try{
			Usuario usuario = (Usuario) request.getSession().getAttribute("user");
			List<Equipo> equipos = servicioEquipo.getListaDeEquiposByIdUsuario(usuario.getId());
			List<Partido> partidos = servicioPartido.getListaDePartidosNoFinalizadosByListaDeEquipos(equipos);
			List<Integer> fechaNumero = new ArrayList<Integer>();
			for (Partido p : partidos) {
				fechaNumero.add(servicioFecha.getCantidadDeFechasDeUnTorneo(p.getFecha().getTorneo()));
			}
			modelo.put("fechaNumero", fechaNumero);
			modelo.put("partidos", partidos);
			return new ModelAndView("home", modelo);
		}
		catch(Exception e){
			return new ModelAndView("home");
		}	
	}

	@RequestMapping(path = "/", method = RequestMethod.GET)
	public ModelAndView inicio() {
		return new ModelAndView("redirect:/home");
	}

	@RequestMapping("/registrar")
	public ModelAndView registrar() {

		ModelMap modelo = new ModelMap();
		Usuario usuario = new Usuario();
		modelo.put("usuario", usuario);
		return new ModelAndView("registrar", modelo);
	}

	@RequestMapping(path = "/registrar-usuario", method = RequestMethod.POST)
	public ModelAndView registrarUsuario(@ModelAttribute("usuario") Usuario usuario, HttpServletRequest request) {
		ModelMap model = new ModelMap();
		Integer usuarioBuscado = servicioUsuario.consultarExistencia(usuario);
		if (usuarioBuscado == 0) {
			servicioUsuario.guardarUsuario(usuario);
			request.getSession().setAttribute("user", usuario);
			return new ModelAndView("redirect:/home");
		} else {
			model.put("error", "El usuario ya existe");
		}
		return new ModelAndView("registrar", model);
	}

	@RequestMapping("/logout")
	public ModelAndView logout(HttpServletRequest request) {
		request.getSession().removeAttribute("user");
		return new ModelAndView("redirect:/home");
	}

}
