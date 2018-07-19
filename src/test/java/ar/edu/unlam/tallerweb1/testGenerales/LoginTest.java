package ar.edu.unlam.tallerweb1.testGenerales;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.EquipoDao;
import ar.edu.unlam.tallerweb1.dao.FechaDao;
import ar.edu.unlam.tallerweb1.modelo.Equipo;
import ar.edu.unlam.tallerweb1.modelo.Fecha;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioEquipoImpl;
import ar.edu.unlam.tallerweb1.servicios.ServicioFechaImpl;

public class LoginTest {

    private ServicioEquipoImpl EquipoService;
    
    private ServicioFechaImpl FechaService;

    private EquipoDao EquipoDao;
    private FechaDao FechaDao;

    @Before
    public void setUp(){
    	EquipoDao = mock(EquipoDao.class);
        
        when(EquipoDao.getListaDeEquiposByIdTorneo(1L))
        .thenReturn(null);
        
		Equipo unEquipo = new Equipo();
		Usuario unUsuario = new Usuario();
		unUsuario.setId(1L);
		unEquipo.setUsuario(unUsuario);
		Equipo equipoDos = new Equipo();
		Usuario usuarioDos = new Usuario();
		usuarioDos.setId(3L);
		equipoDos.setUsuario(usuarioDos);
		List<Equipo> listaEquipo = new ArrayList<Equipo>();
		listaEquipo.add(unEquipo);
		listaEquipo.add(equipoDos);
		
        when(EquipoDao.getListaDeEquiposByIdTorneo(2L))
        .thenReturn(listaEquipo);
        
        EquipoService = new ServicioEquipoImpl();
        EquipoService.setDao(EquipoDao);
        
        
    	FechaDao = mock(FechaDao.class);
        
        when(FechaDao.getFechasDeUnTorneoByIdTorneo(1L))
        .thenReturn(null);
        
		Fecha FechaUno = new Fecha();
		Fecha FechaDos = new Fecha();
		List<Fecha> listaFechas = new ArrayList<Fecha>();
		listaFechas.add(FechaUno);
		listaFechas.add(FechaDos);
		
        when(FechaDao.getFechasDeUnTorneoByIdTorneo(2L))
        .thenReturn(listaFechas);
        
        FechaService = new ServicioFechaImpl();
        FechaService.setDao(FechaDao);
        
    }

    @Test(expected = Exception.class)
    @Transactional
    @Rollback
    public void testConsultarCantidadDeEquiposRegistradosEnElTorneoPorUsuarioSeEsperaExcepcion(){
    	EquipoService.getCantidadDeEquiposRegistradorEnElTorneoPorElUsuario(1L, 1L);
    }
    
    @Test
    @Transactional
    @Rollback
    public void testConsultarCantidadDeEquiposRegistradosEnElTorneoPorUsuarioSeEsperaTrue(){
    	EquipoService.getCantidadDeEquiposRegistradorEnElTorneoPorElUsuario(2L, 1L);
    }
    
    @Test(expected = Exception.class)
    @Transactional
    @Rollback
    public void testConsultarCantidadDeEquiposRegistradosEnElTorneo(){
    	FechaService.getCantidadDeFechasActivasDeUnTorneo(1L);
    }
    
    @Test
    @Transactional
    @Rollback
    public void alConsultatExistenciaSeEsperaExcepcion(){
    	FechaService.getCantidadDeFechasActivasDeUnTorneo(2L);
    }
}
