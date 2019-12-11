/* Copyright 2018-2019 by company.,
 *
 * Company Address.
 * All rights reserved.
 */
package com.company.bookstore.util;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * The Class HibernateUtil.
 */
public class HibernateUtil {

	private final static Logger logger = Logger.getLogger(HibernateUtil.class);

	/** The registry. */
	private static StandardServiceRegistry registry;

	/** The session factory. */
	private static SessionFactory sessionFactory;

	/** The session. */
	private static Session session;

	/**
	 * This method used to get the session factory instance.
	 *
	 * @return the session factory
	 */
	public static SessionFactory getSessionFactory() {
		logger.debug("HibernateUtil:: getSessionFactory() is being invoked");
		if (sessionFactory == null) {
			try {
				// Create registry
				registry = new StandardServiceRegistryBuilder().configure().build();

				// Create MetadataSources
				MetadataSources sources = new MetadataSources(registry);

				// Create Metadata
				Metadata metadata = sources.getMetadataBuilder().build();

				// Create SessionFactory
				sessionFactory = metadata.getSessionFactoryBuilder().build();

			} catch (Exception ex) {
				logger.error("HibernateUtil:: getSessionFactory():  An error occurred while getting session factory",
						ex);
				if (registry != null) {
					StandardServiceRegistryBuilder.destroy(registry);
				}
			}
		}
		logger.debug("HibernateUtil:: getSessionFactory() is ended");
		return sessionFactory;
	}

	/**
	 * This method used to get the session instance.
	 *
	 * @return the session
	 */
	public static Session getSession() {
		logger.debug("HibernateUtil:: getSession() is being invoked");
		sessionFactory = getSessionFactory();
		try {
			session = sessionFactory.openSession();
		} catch (Throwable t) {
			logger.error("HibernateUtil:: getSession():  An error occurred while getting session", t);
		}
		if (session == null) {
			logger.error("HibernateUtil:: getSession():  session is discovered null");
		}
		logger.debug("HibernateUtil:: getSession() is ended");
		return session;
	}

	/**
	 * This method used to shutdown the registry.
	 */
	public static void shutdown() {
		logger.debug("HibernateUtil:: shutdown() is being invoked");
		if (registry != null) {
			StandardServiceRegistryBuilder.destroy(registry);
		}
		logger.debug("HibernateUtil:: shutdown() is ended");
	}
}
