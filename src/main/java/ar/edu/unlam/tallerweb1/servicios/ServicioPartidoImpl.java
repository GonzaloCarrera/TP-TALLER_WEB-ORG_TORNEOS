package ar.edu.unlam.tallerweb1.servicios;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.PartidoDao;
import ar.edu.unlam.tallerweb1.modelo.Equipo;
import ar.edu.unlam.tallerweb1.modelo.Estadistica;
import ar.edu.unlam.tallerweb1.modelo.Fecha;
import ar.edu.unlam.tallerweb1.modelo.Partido;
import ar.edu.unlam.tallerweb1.modelo.Torneo;
@Service("servicioPartido")
@Transactional
public class ServicioPartidoImpl implements ServicioPartido {

	@Inject
	private PartidoDao partidoDao;

	@Override
	public void guardarPartido(Partido partido) {
		partidoDao.guardarPartido(partido);		
	}

	@Override
	public List<Partido> getListaDePartidosNoFinalizadosByListaDeEquipos(List<Equipo> equipos) {
		return partidoDao.getListaDePartidosNoFinalizadosByListaDeEquipos(equipos);
	}

	@Override
	public List<Partido> getListaDePartidosNoFinalizados() {
		return partidoDao.getListaDePartidosNoFinalizados();
	}

	@Override
	public List<Partido> getListaDePartidosDeLaFechaYTorneo(Fecha fecha, Torneo torneo) {
		return partidoDao.getListaDePartidosDeLaFechaYTorneo(fecha, torneo);
	}

	@Override
	public List<Partido> getListaDePartidosDelTorneo(Torneo torneo) {
		return partidoDao.getListaDePartidosDelTorneo(torneo);
	}

	@Override
	public List<Partido> getListaDePartidosDeLaFecha(Long idFecha) {
		return partidoDao.getListaDePartidosDeLaFecha(idFecha);
	}

	@Override
	public Partido getPartidoByIdPartido(Long idPartido) {
		return partidoDao.getPartidoByIdPartido(idPartido);
	}
	
	@Override
	public List<Estadistica> getTablaDePosicionesByTorneo(Torneo torneo) {
		List<Partido> partidos = this.getListaDePartidosDelTorneoFinalizados(torneo);

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
					}
					if(rank.indexOf(statsE2)!=-1){
						statsE2.setPuntos(rank.get(rank.indexOf(statsE2)).getPuntos()+1);
						statsE2.setPartidosEmpatados(rank.get(rank.indexOf(statsE2)).getPartidosEmpatados()+1);
						statsE2.setPartidosGanados(rank.get(rank.indexOf(statsE2)).getPartidosGanados());
						statsE2.setPartidosPerdidos(rank.get(rank.indexOf(statsE2)).getPartidosPerdidos());
					}
					else{
						statsE2.setPartidosEmpatados(1L);
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
		return rank;
	}

	@Override
	public List<Partido> getListaDePartidosDelTorneoFinalizados(Torneo torneo) {
		return partidoDao.getListaDePartidosDelTorneoFinalizados(torneo);
	}

	
}
