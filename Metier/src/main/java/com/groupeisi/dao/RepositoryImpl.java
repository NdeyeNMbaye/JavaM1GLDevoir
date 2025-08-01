package com.groupeisi.dao;

import com.groupeisi.config.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.List;

public class RepositoryImpl<T> implements Repository<T> {

	private final Class<T> entityClass;

	public RepositoryImpl(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	@Override
	public boolean save(T t) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.persist(t); // Utilisation de persist() qui est la méthode recommandée pour les nouvelles entités
			transaction.commit();
			return true;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(int id) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			T entity = session.find(entityClass, id); // Utilisation de find() qui est l'API standard JPA
			if (entity != null) {
				session.remove(entity); // Utilisation de remove() qui est l'API standard JPA
				transaction.commit();
				return true;
			}
			return false;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(T t) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.merge(t);
			transaction.commit();
			return true;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			return false;
		}
	}

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

	@Override
	public T get(int id) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.find(entityClass, id); // Utilisation de find() qui est l'API standard JPA
		}
	}
}