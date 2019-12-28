package Llops.DataBase;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
			p.setTorn(0);

			// Roles
			Rol lobo = new Rol("llop", 4, "", "");
			Rol alcalde = new Rol("alcalde", 1, "", "");
			Rol vilata = new Rol("vilata", 1, "", "");
			Rol vident = new Rol("vident", 1, "", "");

			// Usuarios
			User carlos = new User("Carlos", hashPass("Carlos", "1234"), "TheCaSeVi", "pathImage");
			User adri = new User("Adrian", hashPass("Adrian", "1234"), "Adri", "pathImage");
			User victor = new User("Victor", hashPass("Victor", "1234"), "Vic", "pathImage");
			User carlos2 = new User("Carlos2", hashPass("Carlos2", "1234"), "TheCaSeVi", "pathImage");
			User adri2 = new User("Adrian2", hashPass("Adrian2", "1234"), "Adri", "pathImage");
			User victor2 = new User("Victor2", hashPass("Victor2", "1234"), "Vic", "pathImage");

			// Save de roles
			session.saveOrUpdate(lobo);
			session.saveOrUpdate(alcalde);
			session.saveOrUpdate(vilata);
			session.saveOrUpdate(vident);

			// Save de usuarios
			session.saveOrUpdate(carlos);
			session.saveOrUpdate(adri);
			session.saveOrUpdate(victor);
			session.saveOrUpdate(carlos2);
			session.saveOrUpdate(adri2);
			session.saveOrUpdate(victor2);

			session.saveOrUpdate(p);

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

	private static String hashPass(String userName, String password) throws NoSuchAlgorithmException {

		String passForHash = password + userName;

		MessageDigest md = MessageDigest.getInstance("SHA1");

		byte[] messageDigest = md.digest(passForHash.getBytes());

		BigInteger bg = new BigInteger(1, messageDigest);

		String hashPass = bg.toString(16);

		while (hashPass.length() < 32) {

			hashPass = "0" + hashPass;

		}
		return hashPass;
	}

}
