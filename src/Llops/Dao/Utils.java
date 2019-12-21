package Llops.Dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

public class Utils {
	
	static Session session;
	static SessionFactory sessionFactory;
	static ServiceRegistry serviceRegistry;
	static MetadataSources sources;

	public static synchronized SessionFactory getSessionFactory() {
	    if ( sessionFactory == null ) {
	        serviceRegistry = new StandardServiceRegistryBuilder()
	                .configure("hibernate.cfg.xml")
	                .build();

	        sources = new MetadataSources( serviceRegistry );
	        
	        Metadata metadata = sources.getMetadataBuilder().build();
	        	
	                
	    }
	    return sessionFactory;
	}

}
