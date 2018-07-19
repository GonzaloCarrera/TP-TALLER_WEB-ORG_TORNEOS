package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.dao.UsuarioDao;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public interface ServicioUsuario {
	Usuario consultarUsuario(Usuario usuario);
	Integer consultarExistencia(Usuario usuario);
	void guardarUsuario(Usuario usuario);
	Usuario getUsuarioById(Long id);
}
