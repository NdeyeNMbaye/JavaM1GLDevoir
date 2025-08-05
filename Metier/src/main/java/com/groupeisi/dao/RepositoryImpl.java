// Fichier : com/groupeisi/dao/RepositoryImpl.java

package com.groupeisi.dao;

import com.groupeisi.config.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * Implémentation du pattern Repository pour une entité donnée.
 * La classe utilise une session Hibernate et gère les transactions.
 *
 * @param <T> Le type d'entité que ce repository va gérer.
 */
public class RepositoryImpl<T> implements Repository<T> {

	private final Class<T> entityClass;

	public RepositoryImpl(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	/**
	 * Sauvegarde ou met à jour une entité dans la base de données.
	 * Utilise session.merge() pour gérer à la fois les nouvelles entités et les entités détachées.
	 *
	 * @param t L'entité à sauvegarder ou mettre à jour.
	 * @return true si l'opération a réussi, false sinon.
	 */
	@Override
	public boolean save(T t) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			// Utilisation de merge() au lieu de persist() pour gérer les entités détachées.
			session.merge(t);
			transaction.commit();
			return true;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			System.err.println("Erreur lors de la sauvegarde/mise à jour de l'entité : " + e.getMessage());
			e.printStackTrace();
			return false;
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	/**
	 * Supprime une entité par son ID.
	 *
	 * @param id L'identifiant de l'entité à supprimer.
	 * @return true si l'entité a été supprimée, false sinon.
	 */
	@Override
	public boolean delete(int id) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			T entity = session.find(entityClass, id);
			if (entity != null) {
				session.remove(entity);
				transaction.commit();
				return true;
			}
			// Si l'entité n'est pas trouvée, la transaction est quand même validée.
			transaction.commit();
			return false;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			System.err.println("Erreur lors de la suppression de l'entité " + entityClass.getSimpleName() + " avec l'ID " + id + " : " + e.getMessage());
			e.printStackTrace();
			return false;
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	/**
	 * Met à jour une entité existante.
	 *
	 * @param t L'entité mise à jour.
	 * @return true si l'entité a été mise à jour, false sinon.
	 */
	@Override
	public boolean update(T t) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.merge(t);
			transaction.commit();
			return true;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			System.err.println("Erreur lors de la mise à jour de l'entité : " + e.getMessage());
			e.printStackTrace();
			return false;
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	/**
	 * Récupère toutes les entités du type géré.
	 * Cette méthode est en lecture seule, le try-with-resources est donc adapté.
	 *
	 * @return Une liste de toutes les entités.
	 */
	@Override
	public List<T> list() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<T> cq = cb.createQuery(entityClass);
			Root<T> root = cq.from(entityClass);
			cq.select(root);
			return session.createQuery(cq).getResultList();
		}
	}

	/**
	 * Récupère une entité par son ID.
	 * Cette méthode est en lecture seule, le try-with-resources est donc adapté.
	 *
	 * @param id L'identifiant de l'entité.
	 * @return L'entité trouvée, ou null si elle n'existe pas.
	 */
	@Override
	public T get(int id) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.find(entityClass, id);
		}
	}
}
