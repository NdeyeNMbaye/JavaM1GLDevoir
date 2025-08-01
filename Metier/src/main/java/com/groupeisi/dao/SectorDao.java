package com.groupeisi.dao;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

import org.hibernate.Session;

import com.groupeisi.config.HibernateUtil;
import com.groupeisi.entity.SectorEntity;

public class SectorDao extends RepositoryImpl<SectorEntity> implements ISectorDao {

    public SectorDao() {
        super(SectorEntity.class);
    }

    public Optional<SectorEntity> findByName(String name) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<SectorEntity> cq = cb.createQuery(SectorEntity.class);
            Root<SectorEntity> root = cq.from(SectorEntity.class);

            Predicate predicate = cb.equal(root.get("name"), name);
            cq.select(root).where(predicate);

            return Optional.ofNullable(session.createQuery(cq).uniqueResult());
        }
    }
}