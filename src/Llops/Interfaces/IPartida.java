package Llops.Interfaces;

import java.util.List;
import java.util.Set;

import Llops.Mort;
import Llops.Partida;
import Llops.Rol;
import Llops.User;
import Llops.Vot;
import Llops.XatMessage;
import Llops.Dao.IGenericDao;

public interface IPartida extends IGenericDao<Partida, Integer>{

	void inici(Partida partida);

	void fiTorn(Partida partida);
	
	List<User> jugadorsVius(Partida partida);
	
	List<Rol> rolsVius(Partida partida);
	
	Set<XatMessage> getXat(Partida partida);
	
	Set<XatMessage> getXatLlops(Partida partida);
	
	Set<Vot> getHistorial(Partida partida);
	
	Set<Mort> getMorts(Partida partida);
}
