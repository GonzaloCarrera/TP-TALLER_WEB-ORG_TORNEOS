package ar.edu.unlam.tallerweb1.testGenerales;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Equipo;
import ar.edu.unlam.tallerweb1.modelo.Estadistica;
import ar.edu.unlam.tallerweb1.modelo.Fecha;
import ar.edu.unlam.tallerweb1.modelo.Partido;
import ar.edu.unlam.tallerweb1.modelo.Torneo;

public class PartidoTest extends SpringTest{
	@Test
	@Transactional
	@SuppressWarnings("unchecked")
	public void testGetListaDePartidosNoFinalizados() {
		
		Partido unPartido = new Partido();
		unPartido.setFinalizado(true);
		
		Partido PartidoDos = new Partido();
		Partido PartidoTres = new Partido();
		
		Session session = getSession();
		
		session.saveOrUpdate(unPartido);
		session.saveOrUpdate(PartidoDos);
		session.saveOrUpdate(PartidoTres);
		
		List<Partido> listaDePartidos = new ArrayList<Partido>();
		List<Partido> partidos = session.createCriteria(Partido.class)
				.add(Restrictions.eq("finalizado", false))
				.list();
		for(Partido p : partidos){
			if(!listaDePartidos.contains(p)){
				listaDePartidos.add(p);
			}
		}

		Assert.assertTrue(listaDePartidos.size() == 2);
	}
	
	@Test
	@Transactional
	@SuppressWarnings("unchecked")
	public void testGetListaDePartidosNoFinalizadosByListaDeEquipos() {
		Equipo unEquipo = new Equipo();
		Equipo EquipoDos = new Equipo();
		Equipo EquipoTres = new Equipo();
		Equipo EquipoCuatro = new Equipo();
		Equipo EquipoCinco = new Equipo();
		Equipo EquipoSeis = new Equipo();
		
		Partido unPartido = new Partido();
		unPartido.setFinalizado(true);
		unPartido.setEquipo1(unEquipo);
		unPartido.setEquipo2(EquipoDos);
		Partido PartidoDos = new Partido();
		PartidoDos.setEquipo1(EquipoTres);
		PartidoDos.setEquipo2(EquipoCuatro);
		Partido PartidoTres = new Partido();
		PartidoTres.setEquipo1(EquipoCinco);
		PartidoTres.setEquipo2(EquipoSeis);
		
		
		Session session = getSession();
		
		session.saveOrUpdate(unEquipo);
		session.saveOrUpdate(EquipoDos);
		session.saveOrUpdate(EquipoTres);
		session.saveOrUpdate(EquipoCuatro);
		session.saveOrUpdate(EquipoCinco);
		session.saveOrUpdate(EquipoSeis);
		
		session.saveOrUpdate(unPartido);
		session.saveOrUpdate(PartidoDos);
		session.saveOrUpdate(PartidoTres);
		
		List<Equipo> equipos = new ArrayList<Equipo>();
		equipos.add(unEquipo);
		equipos.add(EquipoDos);
		equipos.add(EquipoTres);
		equipos.add(EquipoCuatro);
		equipos.add(EquipoCinco);
		equipos.add(EquipoSeis);
		
		List<Partido> listaDePartidos = new ArrayList<Partido>();
		List<Partido> partidos = session.createCriteria(Partido.class)
				.add(Restrictions.eq("finalizado", false))
				.list();
		for(Partido p : partidos){
			if(!listaDePartidos.contains(p)){
				listaDePartidos.add(p);
			}
		}
		List<Partido> partidosHabilitados = listaDePartidos;
		List<Partido> listaDePartidosNoFinalizadosDelUsuario = new ArrayList<Partido>();
		for(Partido p : partidosHabilitados){
			for(Equipo e : equipos){
				if(p.getEquipo1().getId()==e.getId()||p.getEquipo2().getId()==e.getId()){
					listaDePartidosNoFinalizadosDelUsuario.add(p);
				}
			}
		}

		Assert.assertTrue(listaDePartidosNoFinalizadosDelUsuario.size() == 4);
	}

	@Test
	@Transactional
	@SuppressWarnings("unchecked")
	public void testGetListaDePartidosDeLaFechaYTorneo() {
		Equipo unEquipo = new Equipo();
		Equipo EquipoDos = new Equipo();
		Equipo EquipoTres = new Equipo();
		Equipo EquipoCuatro = new Equipo();
		
		Fecha unaFecha = new Fecha();
		Fecha fechaDos = new Fecha();
		Torneo unTorneo = new Torneo();
		Torneo TorneoDos = new Torneo();
		unaFecha.setTorneo(unTorneo);
		fechaDos.setTorneo(TorneoDos);
		
		Partido unPartido = new Partido();
		unPartido.setFinalizado(true);
		unPartido.setEquipo1(unEquipo);
		unPartido.setEquipo2(EquipoDos);
		unPartido.setFecha(unaFecha);

		Partido PartidoDos = new Partido();
		PartidoDos.setEquipo1(EquipoTres);
		PartidoDos.setEquipo2(EquipoCuatro);
		PartidoDos.setFecha(fechaDos);
		
		
		Session session = getSession();
		
		session.saveOrUpdate(unEquipo);
		session.saveOrUpdate(EquipoDos);
		session.saveOrUpdate(EquipoTres);
		session.saveOrUpdate(EquipoCuatro);
		
		session.saveOrUpdate(unaFecha);
		session.saveOrUpdate(fechaDos);
		session.saveOrUpdate(unTorneo);
		session.saveOrUpdate(TorneoDos);
		
		session.saveOrUpdate(unPartido);
		session.saveOrUpdate(PartidoDos);
		
		
		List<Partido> listaDePartidos = new ArrayList<Partido>();
		List<Partido> partidos = session.createCriteria(Partido.class)
				.add(Restrictions.eq("fecha", unaFecha))
				.createAlias("fecha", "f")
				.add(Restrictions.eq("f.torneo", unTorneo))
				.list();
		for(Partido p : partidos){
			if(!listaDePartidos.contains(p)){
				listaDePartidos.add(p);
			}
		}	

		Assert.assertTrue(listaDePartidos.size() == 1);
	}
	
	@Test
	@Transactional
	@SuppressWarnings("unchecked")
	public void testGetListaDePartidosDeLaFecha() {
		Equipo unEquipo = new Equipo();
		Equipo EquipoDos = new Equipo();
		Equipo EquipoTres = new Equipo();
		Equipo EquipoCuatro = new Equipo();
		
		Fecha unaFecha = new Fecha();
		Fecha fechaDos = new Fecha();
		Torneo unTorneo = new Torneo();
		Torneo TorneoDos = new Torneo();
		unaFecha.setTorneo(unTorneo);
		fechaDos.setTorneo(TorneoDos);
		
		Partido unPartido = new Partido();
		
		unPartido.setEquipo1(unEquipo);
		unPartido.setEquipo2(EquipoDos);
		unPartido.setFecha(unaFecha);
				
		Partido PartidoDos = new Partido();
		PartidoDos.setEquipo1(EquipoTres);
		PartidoDos.setEquipo2(EquipoCuatro);
		PartidoDos.setFecha(fechaDos);
		PartidoDos.setFinalizado(true);
		
		Session session = getSession();
		
		session.saveOrUpdate(unEquipo);
		session.saveOrUpdate(EquipoDos);
		session.saveOrUpdate(EquipoTres);
		session.saveOrUpdate(EquipoCuatro);
		
		session.saveOrUpdate(unaFecha);
		session.saveOrUpdate(fechaDos);
		session.saveOrUpdate(unTorneo);
		session.saveOrUpdate(TorneoDos);
		
		session.saveOrUpdate(unPartido);
		session.saveOrUpdate(PartidoDos);
		
		
		List<Partido> listaDePartidos = new ArrayList<Partido>();
		List<Partido> partidos = session.createCriteria(Partido.class)
				.add(Restrictions.eq("finalizado", false))
				.createAlias("fecha", "f")
				.add(Restrictions.eq("f.id", unaFecha.getId()))
				.list();
		for(Partido p : partidos){
			if(!listaDePartidos.contains(p)){
				listaDePartidos.add(p);
			}
		}	

		Assert.assertTrue(listaDePartidos.size() == 1);
	}
	
	@Test
	@Transactional
	@SuppressWarnings("unchecked")
	public void testGetListaDePartidosDelTorneo() {
		Equipo unEquipo = new Equipo();
		Equipo EquipoDos = new Equipo();
		Equipo EquipoTres = new Equipo();
		Equipo EquipoCuatro = new Equipo();
		
		Fecha unaFecha = new Fecha();
		Fecha fechaDos = new Fecha();
		Torneo unTorneo = new Torneo();
		Torneo TorneoDos = new Torneo();
		unaFecha.setTorneo(unTorneo);
		fechaDos.setTorneo(TorneoDos);
		
		Partido unPartido = new Partido();
		
		unPartido.setEquipo1(unEquipo);
		unPartido.setEquipo2(EquipoDos);
		unPartido.setFecha(unaFecha);
				
		Partido PartidoDos = new Partido();
		PartidoDos.setEquipo1(EquipoTres);
		PartidoDos.setEquipo2(EquipoCuatro);
		PartidoDos.setFecha(fechaDos);
		PartidoDos.setFinalizado(true);
		
		Session session = getSession();
		
		session.saveOrUpdate(unEquipo);
		session.saveOrUpdate(EquipoDos);
		session.saveOrUpdate(EquipoTres);
		session.saveOrUpdate(EquipoCuatro);
		
		session.saveOrUpdate(unaFecha);
		session.saveOrUpdate(fechaDos);
		session.saveOrUpdate(unTorneo);
		session.saveOrUpdate(TorneoDos);
		
		session.saveOrUpdate(unPartido);
		session.saveOrUpdate(PartidoDos);
		
		
		List<Partido> listaDePartidos = new ArrayList<Partido>();
		List<Partido> partidos = getSession().createCriteria(Partido.class)
				.createAlias("fecha", "f")
				.add(Restrictions.eq("f.torneo", unTorneo))
				.list();
		for(Partido p : partidos){
			if(!listaDePartidos.contains(p)){
				listaDePartidos.add(p);
			}
		}	

		Assert.assertTrue(listaDePartidos.size() == 1);
	}
	
	
	@Test
	@Transactional
	@SuppressWarnings("unchecked")
	public void testGetListaDePartidosDelTorneoFinalizados() {
		Equipo unEquipo = new Equipo();
		Equipo EquipoDos = new Equipo();
		Equipo EquipoTres = new Equipo();
		Equipo EquipoCuatro = new Equipo();
		
		Fecha unaFecha = new Fecha();
		Fecha fechaDos = new Fecha();
		Torneo unTorneo = new Torneo();
		Torneo TorneoDos = new Torneo();
		unaFecha.setTorneo(unTorneo);
		fechaDos.setTorneo(TorneoDos);
		
		Partido unPartido = new Partido();
		
		unPartido.setEquipo1(unEquipo);
		unPartido.setEquipo2(EquipoDos);
		unPartido.setFecha(unaFecha);
				
		Partido PartidoDos = new Partido();
		PartidoDos.setEquipo1(EquipoTres);
		PartidoDos.setEquipo2(EquipoCuatro);
		PartidoDos.setFecha(fechaDos);
		PartidoDos.setFinalizado(true);
		
		Session session = getSession();
		
		session.saveOrUpdate(unEquipo);
		session.saveOrUpdate(EquipoDos);
		session.saveOrUpdate(EquipoTres);
		session.saveOrUpdate(EquipoCuatro);
		
		session.saveOrUpdate(unaFecha);
		session.saveOrUpdate(fechaDos);
		session.saveOrUpdate(unTorneo);
		session.saveOrUpdate(TorneoDos);
		
		session.saveOrUpdate(unPartido);
		session.saveOrUpdate(PartidoDos);
		
		
		List<Partido> listaDePartidos = new ArrayList<Partido>();
		List<Partido> partidos = getSession().createCriteria(Partido.class)
				.createAlias("fecha", "f")
				.add(Restrictions.eq("f.torneo", TorneoDos))
				.add(Restrictions.eq("finalizado", true))
				.list();
		for(Partido p : partidos){
			if(!listaDePartidos.contains(p)){
				listaDePartidos.add(p);
			}
		}	

		Assert.assertTrue(listaDePartidos.size() == 1);
	}
	
	@Test
	@Transactional
	@SuppressWarnings("unchecked")
	public void testGetPartidoByIdPartido() {
		Equipo unEquipo = new Equipo();
		Equipo EquipoDos = new Equipo();
		Equipo EquipoTres = new Equipo();
		Equipo EquipoCuatro = new Equipo();
		
		Fecha unaFecha = new Fecha();
		Fecha fechaDos = new Fecha();
		Torneo unTorneo = new Torneo();
		Torneo TorneoDos = new Torneo();
		unaFecha.setTorneo(unTorneo);
		fechaDos.setTorneo(TorneoDos);
		
		Partido unPartido = new Partido();
		
		unPartido.setEquipo1(unEquipo);
		unPartido.setEquipo2(EquipoDos);
		unPartido.setFecha(unaFecha);
				
		Partido PartidoDos = new Partido();
		PartidoDos.setEquipo1(EquipoTres);
		PartidoDos.setEquipo2(EquipoCuatro);
		PartidoDos.setFecha(fechaDos);
		PartidoDos.setFinalizado(true);
		
		Session session = getSession();
		
		session.saveOrUpdate(unEquipo);
		session.saveOrUpdate(EquipoDos);
		session.saveOrUpdate(EquipoTres);
		session.saveOrUpdate(EquipoCuatro);
		
		session.saveOrUpdate(unaFecha);
		session.saveOrUpdate(fechaDos);
		session.saveOrUpdate(unTorneo);
		session.saveOrUpdate(TorneoDos);
		
		session.saveOrUpdate(unPartido);
		session.saveOrUpdate(PartidoDos);
		
		Partido partido = new Partido();
		List<Partido> listaDePartidos = new ArrayList<Partido>();
		List<Partido> partidos = getSession().createCriteria(Partido.class)
				.add(Restrictions.eq("id", PartidoDos.getId()))
				.list();
		for(Partido p : partidos){
			if(!listaDePartidos.contains(p)){
				listaDePartidos.add(p);
				partido = p;
			}
		}	

		Assert.assertTrue(listaDePartidos.size() == 1);
	}
	
	@Test
	@Transactional
	@SuppressWarnings("unchecked")
	public void testGetTablaDePosicionesByTorneo() {
		Equipo unEquipo = new Equipo();
		
		Equipo EquipoDos = new Equipo();
		Equipo EquipoTres = new Equipo();
		Equipo EquipoCuatro = new Equipo();
		
		
		Fecha unaFecha = new Fecha();
		Fecha fechaDos = new Fecha();
		Torneo unTorneo = new Torneo();
		Torneo TorneoDos = new Torneo();
		unaFecha.setTorneo(unTorneo);
		fechaDos.setTorneo(TorneoDos);
		
		Partido unPartido = new Partido();
		
		unPartido.setEquipo1(unEquipo);
		unPartido.setGolesEquipo1(new Long(1));
		unPartido.setEquipo2(EquipoDos);
		unPartido.setGolesEquipo2(new Long(2));
		unPartido.setFecha(unaFecha);
				
		Partido PartidoDos = new Partido();
		PartidoDos.setEquipo1(EquipoTres);
		PartidoDos.setGolesEquipo1(new Long(2));
		PartidoDos.setEquipo2(EquipoCuatro);
		PartidoDos.setGolesEquipo2(new Long(0));
		PartidoDos.setFecha(fechaDos);
		PartidoDos.setFinalizado(true);
		
		Session session = getSession();
		
		session.saveOrUpdate(unEquipo);
		session.saveOrUpdate(EquipoDos);
		session.saveOrUpdate(EquipoTres);
		session.saveOrUpdate(EquipoCuatro);
		
		session.saveOrUpdate(unaFecha);
		session.saveOrUpdate(fechaDos);
		session.saveOrUpdate(unTorneo);
		session.saveOrUpdate(TorneoDos);
		
		session.saveOrUpdate(unPartido);
		session.saveOrUpdate(PartidoDos);
		
		
		List<Partido> listaDePartidos = new ArrayList<Partido>();
		List<Partido> partidos = getSession().createCriteria(Partido.class)
				.createAlias("fecha", "f")
				.add(Restrictions.eq("f.torneo", TorneoDos))
				.add(Restrictions.eq("finalizado", true))
				.list();
		for(Partido p : partidos){
			if(!listaDePartidos.contains(p)){
				listaDePartidos.add(p);
			}
		}

		List<Estadistica> rank = new ArrayList<Estadistica>();

		for(Partido p : partidos){
			Estadistica statsE1 = new Estadistica();
			Estadistica statsE2 = new Estadistica();
			statsE1.setEquipo(p.getEquipo1());
			statsE2.setEquipo(p.getEquipo2());
			if(rank.indexOf(statsE1)!=-1){
				statsE1.setGolesEnContra(rank.get(rank.indexOf(statsE1)).getGolesEnContra()+p.getGolesEquipo2());
				statsE1.setGolesAFavor(rank.get(rank.indexOf(statsE1)).getGolesAFavor()+p.getGolesEquipo1());
			}
			else{
				statsE1.setGolesEnContra(p.getGolesEquipo2());
				statsE1.setGolesAFavor(p.getGolesEquipo1());
			}
			if(rank.indexOf(statsE2)!=-1){
				statsE2.setGolesEnContra(rank.get(rank.indexOf(statsE2)).getGolesEnContra()+p.getGolesEquipo1());
				statsE2.setGolesAFavor(rank.get(rank.indexOf(statsE2)).getGolesAFavor()+p.getGolesEquipo2());
			}
			else{
				statsE2.setGolesEnContra(p.getGolesEquipo1());
				statsE2.setGolesAFavor(p.getGolesEquipo2());
			}
			if(p.getGolesEquipo1()>p.getGolesEquipo2()){
				if(rank.indexOf(statsE1)!=-1){
					statsE1.setPuntos(rank.get(rank.indexOf(statsE1)).getPuntos()+3);
					statsE1.setPartidosGanados(rank.get(rank.indexOf(statsE1)).getPartidosGanados()+1);
					statsE1.setPartidosEmpatados(rank.get(rank.indexOf(statsE1)).getPartidosEmpatados());
					statsE1.setPartidosPerdidos(rank.get(rank.indexOf(statsE1)).getPartidosPerdidos());
				}
				else{
					statsE1.setPuntos(3L);
					statsE1.setPartidosGanados(1L);
				}
				if(rank.indexOf(statsE2)!=-1){
					statsE2.setPuntos(rank.get(rank.indexOf(statsE2)).getPuntos());
					statsE2.setPartidosPerdidos(rank.get(rank.indexOf(statsE2)).getPartidosPerdidos()+1);
					statsE2.setPartidosEmpatados(rank.get(rank.indexOf(statsE2)).getPartidosEmpatados());
					statsE2.setPartidosGanados(rank.get(rank.indexOf(statsE2)).getPartidosGanados());
				}
				else{
					statsE2.setPartidosPerdidos(1L);
				}
			}
			else{
				if(p.getGolesEquipo1()<p.getGolesEquipo2()){
					if(rank.indexOf(statsE1)!=-1){
						statsE1.setPuntos(rank.get(rank.indexOf(statsE1)).getPuntos());
						statsE1.setPartidosPerdidos(rank.get(rank.indexOf(statsE1)).getPartidosPerdidos()+1);
						statsE1.setPartidosGanados(rank.get(rank.indexOf(statsE1)).getPartidosGanados());
						statsE1.setPartidosEmpatados(rank.get(rank.indexOf(statsE1)).getPartidosEmpatados());
					}
					else{
						statsE1.setPartidosPerdidos(1L);
					}
					if(rank.indexOf(statsE2)!=-1){
						statsE2.setPuntos(rank.get(rank.indexOf(statsE2)).getPuntos()+3);
						statsE2.setPartidosGanados(rank.get(rank.indexOf(statsE2)).getPartidosGanados()+1);
						statsE2.setPartidosPerdidos(rank.get(rank.indexOf(statsE2)).getPartidosPerdidos());
						statsE2.setPartidosEmpatados(rank.get(rank.indexOf(statsE2)).getPartidosEmpatados());
					}
					else{
						statsE2.setPuntos(3L);
						statsE2.setPartidosGanados(1L);
					}
				}
				else{
					if(rank.indexOf(statsE1)!=-1){
						statsE1.setPuntos(rank.get(rank.indexOf(statsE1)).getPuntos()+1);
						statsE1.setPartidosEmpatados(rank.get(rank.indexOf(statsE1)).getPartidosEmpatados()+1);
						statsE1.setPartidosPerdidos(rank.get(rank.indexOf(statsE1)).getPartidosPerdidos());
						statsE1.setPartidosGanados(rank.get(rank.indexOf(statsE1)).getPartidosGanados());
					}
					else{
						statsE1.setPartidosEmpatados(1L);
						statsE1.setPuntos(1L);
					}
					if(rank.indexOf(statsE2)!=-1){
						statsE2.setPuntos(rank.get(rank.indexOf(statsE2)).getPuntos()+1);
						statsE2.setPartidosEmpatados(rank.get(rank.indexOf(statsE2)).getPartidosEmpatados()+1);
						statsE2.setPartidosGanados(rank.get(rank.indexOf(statsE2)).getPartidosGanados());
						statsE2.setPartidosPerdidos(rank.get(rank.indexOf(statsE2)).getPartidosPerdidos());
					}
					else{
						statsE2.setPartidosEmpatados(1L);
						statsE2.setPuntos(1L);
					}
				}
			}
			if(rank.indexOf(statsE1)!=-1){
				rank.set(rank.indexOf(statsE1),statsE1);
			}
			else{
				rank.add(statsE1);
			}
			if(rank.indexOf(statsE2)!=-1){
				rank.set(rank.indexOf(statsE2),statsE2);
			}
			else{
				rank.add(statsE2);
			}
		}

		Assert.assertTrue(rank.size() == 2);
	}
}
