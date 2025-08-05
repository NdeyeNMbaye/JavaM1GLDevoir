// Fichier : com/groupeisi/config/HibernateUtil.java

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

/**
 * Classe utilitaire pour la configuration d'Hibernate, implémentant le pattern Singleton.
 * Cette version est thread-safe et gère les erreurs d'initialisation de manière appropriée.
 */
public class HibernateUtil {
	private static SessionFactory sessionFactory;
	private static final Logger LOG = LoggerFactory.getLogger(HibernateUtil.class);

	private HibernateUtil() {}

	/**
	 * Retourne l'instance de la SessionFactory.
	 * Cette méthode est synchronisée pour garantir la thread-safety.
	 * Si l'initialisation échoue, elle lève une ExceptionInInitializerError.
	 *
	 * @return La SessionFactory
	 */
	public static synchronized SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			try {
				Configuration configuration = new Configuration();

				Properties settings = new Properties();
				settings.put(AvailableSettings.DRIVER, "org.postgresql.Driver");
				settings.put(AvailableSettings.URL, "jdbc:postgresql://localhost:5432/BaseDevoirJava");
				settings.put(AvailableSettings.USER, "postgres");
				settings.put(AvailableSettings.PASS, "passer"); // Vérifie bien que c'est le bon mot de passe
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
				LOG.info("SessionFactory a été initialisée avec succès.");

			} catch (Exception e) {
				LOG.error("Erreur de configuration Hibernate : ", e);
				// Jette une erreur d'initialisation pour indiquer que la SessionFactory
				// n'a pas pu être construite et empêcher l'application de continuer.
				throw new ExceptionInInitializerError(e);
			}
		}
		return sessionFactory;
	}
}
