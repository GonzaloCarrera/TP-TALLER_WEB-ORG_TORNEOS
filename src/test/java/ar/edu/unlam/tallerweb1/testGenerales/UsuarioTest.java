package ar.edu.unlam.tallerweb1.testGenerales;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Partido;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public class UsuarioTest extends SpringTest{
	
	@Test
	@Transactional
	@SuppressWarnings("unchecked")
	public void testGetListaDePartidosNoFinalizados() {
		
		Usuario unUsuario = new Usuario();
		unUsuario.setEmail("correo1@gmail.com");
		unUsuario.setUsername("Diego");
		unUsuario.setPassword("123456");
		
		Usuario UsuarioDos = new Usuario();
		UsuarioDos.setEmail("correo2@gmail.com");
		UsuarioDos.setUsername("Gustavo");
		UsuarioDos.setPassword("123456");
		
		Session session = getSession();
		
		session.saveOrUpdate(unUsuario);
		session.saveOrUpdate(UsuarioDos);
		
		List<?> resultadoUsuario = session.createCriteria(Usuario.class)
				.add(Restrictions.eq("email", unUsuario.getEmail()))
				.add(Restrictions.eq("password", unUsuario.getPassword()))
				.list();

		Assert.assertTrue(!resultadoUsuario.isEmpty());
	}
	
	@Test
	@Transactional
	@SuppressWarnings("unchecked")
	public void testGetUsuarioById() {
		
		Usuario unUsuario = new Usuario();
		unUsuario.setEmail("correo1@gmail.com");
		unUsuario.setUsername("Diego");
		unUsuario.setPassword("123456");
		
		Usuario UsuarioDos = new Usuario();
		UsuarioDos.setEmail("correo2@gmail.com");
		UsuarioDos.setUsername("Gustavo");
		UsuarioDos.setPassword("123456");
		
		Session session = getSession();
		
		session.saveOrUpdate(unUsuario);
		session.saveOrUpdate(UsuarioDos);
		
		List<?> resultadoUsuario = session.createCriteria(Usuario.class)
				.add(Restrictions.eq("id", unUsuario.getId()))
				.list();

		Assert.assertTrue(!resultadoUsuario.isEmpty());
	}
	
	@Test
	@Transactional
	@SuppressWarnings("unchecked")
	public void testConsultarExistencia() {
		
		Usuario unUsuario = new Usuario();
		unUsuario.setEmail("correo1@gmail.com");
		unUsuario.setUsername("Diego");
		unUsuario.setPassword("123456");
		
		Usuario UsuarioDos = new Usuario();
		UsuarioDos.setEmail("correo2@gmail.com");
		UsuarioDos.setUsername("Gustavo");
		UsuarioDos.setPassword("123456");
		
		Session session = getSession();
		
		session.saveOrUpdate(unUsuario);
		session.saveOrUpdate(UsuarioDos);
		
		List<?> resultadoUsuario = session.createCriteria(Usuario.class)
			    .add( Restrictions.disjunction()
			    	    .add( Restrictions.eq("email", UsuarioDos.getEmail()))
			            .add( Restrictions.eq("username", UsuarioDos.getUsername()) )
			        ) 
			    .list();

		Assert.assertTrue(!resultadoUsuario.isEmpty());
	}
}
