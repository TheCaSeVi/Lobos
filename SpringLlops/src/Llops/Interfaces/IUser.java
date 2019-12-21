package Llops.Interfaces;

import java.util.List;

import Llops.Partida;
import Llops.Rol;
import Llops.User;
import Llops.Dao.IGenericDao;

public interface IUser  extends IGenericDao<User, String>{

	boolean registre(String userName, String password);

	boolean login(String userName, String password);
	
	void unirse(Partida partida, User user);
	
	void vota(User sender, User reciver, Partida partida, int torn);
	
	Rol descobrirRol(User user, Partida partida);

	void escriuMessage(String message, User sender, Partida partida);
	
	void escriuMessageLlop(String message,  User sender, Partida partida);

}
