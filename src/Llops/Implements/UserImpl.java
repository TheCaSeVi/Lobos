package Llops.Implements;

import java.util.Date;
import java.util.List;
import java.util.Set;

import Llops.Message;
import Llops.Partida;
import Llops.Rol;
import Llops.RolJugadorPartida;
import Llops.User;
import Llops.Vot;
import Llops.XatMessage;
import Llops.Dao.GenericDao;
import Llops.Interfaces.IUser;
import Llops.Implements.VotImpl;


public class UserImpl extends GenericDao<User, String> implements IUser{
	
	private PartidaImpl p = new PartidaImpl();
	private VotImpl voti = new VotImpl();
	private XatMessageImpl xatm = new XatMessageImpl();
	
	
	@Override
	public boolean registre(String userName, String password) {
		User user = new User(userName, password);
		boolean saved = false;
		try {
			saveOrUpdate(user);
			saved = true;
		}catch(Exception e){
			saved = false;
		}
		return saved;
	}

	@Override
	public boolean login(String userName, String password) {
		List<Object> users = sessionFactory.getCurrentSession().createQuery("FROM User").list();
		
		// return in this for
		for (Object user : users) {
			User u = (User) user;
			String uname = u.getUserName();
			String pass = u.getPassword();
			if (userName.equals(uname)&&password.equals(pass))return true;
		}
		return false;
	}

	@Override
	public void unirse(Partida partida, User user) {
		Set<User> users = partida.getUsers();
		users.add(user);
		Set<Partida> partides = user.getPartides();
		partides.add(partida);
		partida.setUsers(users);
		user.setPartides(partides);
		saveOrUpdate(user);
		p.saveOrUpdate(partida);
	}

	@Override
	public void vota(User sender, User reciver,Partida partida, int torn) {
		Vot vot = new Vot(sender,reciver,partida,torn); 
		Set<Vot> votsSender = sender.getSendsVots();
		votsSender.add(vot);
		Set<Vot> votsReciver = reciver.getRecivesVots();
		votsReciver.add(vot);
		voti.saveOrUpdate(vot);
		saveOrUpdate(sender);
		saveOrUpdate(reciver);
		p.saveOrUpdate(partida);
	}

	@Override
	public Rol descobrirRol(User user, Partida partida) {
		Set<RolJugadorPartida> rolsPartia = user.getUsersRolsPartida();
		
		// return in this for
		for (RolJugadorPartida rolJugadorPartida : rolsPartia) {
			Partida p = rolJugadorPartida.getPartida();
			User u = rolJugadorPartida.getUser();
			if (u.equals(user)&&p.equals(partida)) {
			Rol rol = rolJugadorPartida.getRol();
			return rol;
			}
		}
		return null;
	}

	@Override
	public void escriuMessage(String message, User sender,  Partida partida) {
		
		if (partida.getTorn()%2==0) {
			Set<XatMessage> xm = partida.getPartidaXat();
			Date date = new Date(System.currentTimeMillis());
			XatMessage x = new XatMessage(sender,partida,message,date);
			xm.add(x);
			partida.setPartidaXat(xm);
			p.saveOrUpdate(partida);
			xatm.saveOrUpdate(x);

		}
		
	}

	@Override
	public void escriuMessageLlop(String message, User sender,Partida partida) {

		Set<XatMessage> xm = partida.getPartidaXat();
		Set<RolJugadorPartida> rolsPartia = sender.getUsersRolsPartida();
		
		// return in this for
		for (RolJugadorPartida rolJugadorPartida : rolsPartia) {
			Partida pa = rolJugadorPartida.getPartida();
			User u = rolJugadorPartida.getUser();
			if (u.equals(sender)&&pa.equals(partida)) {
				Rol rol = rolJugadorPartida.getRol();
				if (rol.getNom().equals("Llop") && partida.getTorn()%2!=0){
					Date date = new Date(System.currentTimeMillis());
					XatMessage x = new XatMessage(sender,partida,message,date);
					xm.add(x);
					partida.setPartidaXat(xm);
					p.saveOrUpdate(partida);
					xatm.saveOrUpdate(x);
				}
			
			}
		}
	}

}
