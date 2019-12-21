package Llops;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.engine.jdbc.connections.internal.UserSuppliedConnectionProviderImpl;
import org.hibernate.service.ServiceRegistry;

public class Main {
	static User user;
	static Session session;
	static SessionFactory sessionFactory;
	static ServiceRegistry serviceRegistry;

	public static void main(String[] args) {
		try {
			session = getSessionFactory().openSession();
			session.beginTransaction();

			Partida p = new Partida();
			User u = new User();
			Message m = new Message();
			Rol r = new Rol();
			RolJugadorPartida rj = new RolJugadorPartida();
			Vot v = new Vot();
			XatMessage xm = new XatMessage();
			Mort mort = new Mort();
			
			u.setUserName("que asco de vida");
			m.setReciveMessage(u);
			m.setSenderMessage(u);
			r.setNom("comePollas");
			r.setDescripcio("comes muchas pollas");
			rj.setPartida(p);
			rj.setRol(r);
			rj.setUser(u);
			v.setReciverVot(u);
			v.setSenderVot(u);
			v.setPartidVot(p);
			xm.setPartidaXatMessage(p);
			xm.setSenderXatMessage(u);
			mort.setPartidaMort(p);
			mort.setUserMort(u);
			
			session.saveOrUpdate(u);
			session.saveOrUpdate(p);
			session.saveOrUpdate(m);
			session.saveOrUpdate(r);
			session.saveOrUpdate(rj);
			session.saveOrUpdate(v);
			session.saveOrUpdate(xm);
			session.saveOrUpdate(mort);
			
			
			session.getTransaction().commit();
			System.out.println("todo ha salido a pedir de Milhouse");

		} catch (Exception sqlException) {
			sqlException.printStackTrace();

			if (null != session.getTransaction()) {
				System.out.println("\n.......Transaction Is Being Rolled Back.......");
				session.getTransaction().rollback();
			}

			sqlException.printStackTrace();

		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

	public static synchronized SessionFactory getSessionFactory() {
		if (sessionFactory == null) {

			serviceRegistry = new StandardServiceRegistryBuilder().configure("hibernate_update.cfg.xml").build();

			sessionFactory = new MetadataSources(serviceRegistry).buildMetadata().buildSessionFactory();
		}
		return sessionFactory;
	}

}
