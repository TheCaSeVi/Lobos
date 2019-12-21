package Llops.Dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;


//DAO Generico, funciona mediante genericos
public class GenericDao<T, ID extends Serializable> implements IGenericDao<T, ID> {

	 protected SessionFactory sessionFactory;

	// private final static Logger LOGGER =
	// Logger.getLogger(GenericDao.class.getName());

	public GenericDao() {
		sessionFactory = Utils.getSessionFactory();
	}

	@Override
	//Podemos crear metodos con genericos ya que todos los saveorupdate seran iguales
	public void saveOrUpdate(T entity) {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(entity);
			session.getTransaction().commit();

		} catch (HibernateException e) {
			e.printStackTrace();
			if (session != null && session.getTransaction() != null) {
				System.out.println("\n.......Transaction Is Being Rolled Back.......");
				session.getTransaction().rollback();
			}
			e.printStackTrace();

		}

	}

	@Override
	public T get(ID id) {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			T entity = (T) session.get(getEntityClass(), id);
			session.getTransaction().commit();

			return entity;
		} catch (HibernateException e) {
			e.printStackTrace();
			if (session != null && session.getTransaction() != null) {
				System.out.println("\n.......Transaction Is Being Rolled Back.......");
				session.getTransaction().rollback();
			}
			e.printStackTrace();
			return null;

		}
	}

	@Override
	public void delete(ID id) {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			T entity = (T) session.get(getEntityClass(), id);
			session.delete(entity);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			if (session != null && session.getTransaction() != null) {
				System.out.println("\n.......Transaction Is Being Rolled Back.......");
				session.getTransaction().rollback();
			}
			e.printStackTrace();

		}
	}

	@Override
	public List<T> list() {
		Session session = sessionFactory.getCurrentSession();
		try {

			Query query = session.createQuery("SELECT e FROM " + getEntityClass().getName() + " e");
			List<T> entities = query.list();

			return entities;
		} catch (HibernateException e) {
			e.printStackTrace();
			if (session != null && session.getTransaction() != null) {
				System.out.println("\n.......Transaction Is Being Rolled Back.......");
				session.getTransaction().rollback();
			}
			e.printStackTrace();
			return null;

		}
	}

	private Class<T> getEntityClass() {
		return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
}