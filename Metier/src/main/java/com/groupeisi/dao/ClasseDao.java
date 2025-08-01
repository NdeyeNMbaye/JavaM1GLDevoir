package com.groupeisi.dao;

import com.groupeisi.config.HibernateUtil;
import com.groupeisi.entity.ClasseEntity;
import org.hibernate.Session;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

public class ClasseDao extends RepositoryImpl<ClasseEntity> implements IClasseDao {

    public ClasseDao() {
        super(ClasseEntity.class);
    }

    @Override
    public Optional<ClasseEntity> findByCode(String code) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<ClasseEntity> cq = cb.createQuery(ClasseEntity.class);
            Root<ClasseEntity> root = cq.from(ClasseEntity.class);

            // Correction : Utilisation de "className" au lieu de "code"
            Predicate predicate = cb.equal(root.get("className"), code);
            cq.select(root).where(predicate);

            return Optional.ofNullable(session.createQuery(cq).uniqueResult());
        }
    }

    @Override
    public List<ClasseEntity> findBySectorId(int sectorId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<ClasseEntity> cq = cb.createQuery(ClasseEntity.class);
            Root<ClasseEntity> root = cq.from(ClasseEntity.class);

            Predicate predicate = cb.equal(root.get("sector").get("id"), sectorId);
            cq.select(root).where(predicate);

            return session.createQuery(cq).getResultList();
        }
    }
}