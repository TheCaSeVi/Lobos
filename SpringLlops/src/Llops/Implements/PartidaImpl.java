package Llops.Implements;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import Llops.Mort;
import Llops.Partida;
import Llops.Rol;
import Llops.RolJugadorPartida;
import Llops.User;
import Llops.Vot;
import Llops.XatMessage;
import Llops.Dao.GenericDao;
import Llops.Interfaces.IPartida;

public class PartidaImpl extends GenericDao<Partida, Integer> implements IPartida{

	@Override
	public void inici(Partida partida) {
		Set<User> usersPartida = partida.getUsers();
		
		
		
		
	}

	@Override
	public void fiTorn(Partida partida) {

		int torn = partida.getTorn();
		torn ++;
		partida.setTorn(torn);
		saveOrUpdate(partida);
		
	}

	@Override
	public List<User> jugadorsVius(Partida partida) {

		Set<User> usersPartida = partida.getUsers();
		Set<Mort> mortsPartida = partida.getMortsPartida();
		
		List<User> usersVius = new ArrayList<User>();
		
		for (User user : usersPartida) {
			
			for (Mort mort: mortsPartida) {
				
				if(!user.equals(mort.getUserMort())) {
					usersVius.add(user);
				}
					
			}
		}
		return usersVius;
	}

	@Override
	public List<Rol> rolsVius(Partida partida) {
		Set<RolJugadorPartida> rolspartida = partida.getUsersRolsPartida();
		
		List<Rol> rolsVius = new ArrayList<Rol>();
		
		for (RolJugadorPartida roljugadorpartida : rolspartida) {
			
			if(!roljugadorpartida.getMort()) {
				Rol rol = roljugadorpartida.getRol();
				rolsVius.add(rol);
			}
			
		}
		
		return rolsVius;
	}

	@Override
	public Set<XatMessage> getXat(Partida partida) {
		
		Set<XatMessage> xat = partida.getPartidaXat();
		Set<XatMessage> xatPeople = new HashSet<XatMessage>();
		
		for (XatMessage xatMessage : xat) {
			
			User u = xatMessage.getSenderXatMessage();
			
			Set<RolJugadorPartida> rolsPartides = u.getUsersRolsPartida();
			
			for (RolJugadorPartida rolpartida : rolsPartides) {
				
				if(rolpartida.getPartida().equals(partida)&&!rolpartida.getRol().getNom().equals("Llop")) {
					xatPeople.add(xatMessage);
				}
				
			}
			
		}
		
		return xatPeople;
	}

	@Override
	public Set<XatMessage> getXatLlops(Partida partida) {
		
		Set<XatMessage> xat = partida.getPartidaXat();
		Set<XatMessage> xatLlops = new HashSet<XatMessage>();
		
		for (XatMessage xatMessage : xat) {
			
			User u = xatMessage.getSenderXatMessage();
			
			Set<RolJugadorPartida> rolsPartides = u.getUsersRolsPartida();
			
			for (RolJugadorPartida rolpartida : rolsPartides) {
				
				if(rolpartida.getPartida().equals(partida)&&rolpartida.getRol().getNom().equals("Llop")) {
					xatLlops.add(xatMessage);
				}
				
			}
			
		}
		
		return xatLlops;
	}

	@Override
	public Set<Vot> getHistorial(Partida partida) {
		return partida.getVotsPartida();
	}

	@Override
	public Set<Mort> getMorts(Partida partida) {
		return partida.getMortsPartida();
	}






}
