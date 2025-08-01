package com.groupeisi.config;

import com.groupeisi.entity.ClasseEntity;
import com.groupeisi.entity.SectorEntity;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class HibernateUtil {
	private static SessionFactory sessionFactory;
	private static Logger LOG = LoggerFactory.getLogger(HibernateUtil.class);

	private HibernateUtil() {}

	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			try {
				Configuration configuration = new Configuration();

				Properties settings = new Properties();
				settings.put(AvailableSettings.DRIVER, "org.postgresql.Driver");

				// URL typique postgres : jdbc:postgresql://localhost:5432/nom_de_la_db
				settings.put(AvailableSettings.URL, "jdbc:postgresql://localhost:5432/BaseDevoirJava");
				settings.put(AvailableSettings.USER, "postgres");
				settings.put(AvailableSettings.PASS, "passer"); // ton mot de passe

				settings.put(AvailableSettings.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
				settings.put(AvailableSettings.HBM2DDL_AUTO, "update");

				settings.put(AvailableSettings.SHOW_SQL, "true");
				settings.put(AvailableSettings.FORMAT_SQL, "true");

				settings.put(AvailableSettings.CURRENT_SESSION_CONTEXT_CLASS, "thread");

				configuration.setProperties(settings);

				// Ajout des entités
				configuration.addAnnotatedClass(SectorEntity.class);
				configuration.addAnnotatedClass(ClasseEntity.class);

				ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
						.applySettings(configuration.getProperties()).build();

				sessionFactory = configuration.buildSessionFactory(serviceRegistry);

				return sessionFactory;

			} catch (Exception e) {
				LOG.error("Erreur de configuration Hibernate : {}", e.getMessage());
				e.printStackTrace();
			}
		}
		return sessionFactory;
	}
}
