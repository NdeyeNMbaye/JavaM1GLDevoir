package com.groupeisi.dao;

import com.groupeisi.config.HibernateUtil;
import com.groupeisi.entity.ClasseEntity;
import com.groupeisi.entity.SectorEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.JoinType;
import java.util.List;
import java.util.Optional;

/**
 * Cette classe implémente la couche d'accès aux données pour l'entité ClasseEntity.
 * Elle étend une classe générique et fournit des méthodes spécifiques.
 */
public class ClasseDao extends RepositoryImpl<ClasseEntity> implements IClasseDao {

    public ClasseDao() {
        super(ClasseEntity.class);
    }

    /**
     * Surcharge de la méthode save pour gérer les entités SectorEntity détachées
     * et pour permettre l'ajout ou la mise à jour d'une ClasseEntity.
     * Utilise session.merge() pour gérer les entités nouvelles ou détachées.
     *
     * @param classeEntity L'entité ClasseEntity à sauvegarder ou mettre à jour.
     * @return true si la sauvegarde/mise à jour est réussie, false sinon.
     */
    @Override
    public boolean save(ClasseEntity classeEntity) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            // Si la classeEntity a une SectorEntity associée, nous la fusionnons d'abord
            // pour s'assurer qu'elle est dans un état géré par cette session.
            if (classeEntity.getSector() != null) {
                // merge() retourne une instance gérée de l'entité.
                SectorEntity managedSector = (SectorEntity) session.merge(classeEntity.getSector());
                classeEntity.setSector(managedSector); // Met à jour la ClasseEntity avec la SectorEntity gérée
            }

            // CHANGEMENT IMPORTANT : Utiliser merge() au lieu de persist()
            // merge() gère à la fois les nouvelles entités (sans ID) et les entités détachées (avec ID).
            session.merge(classeEntity);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            System.err.println("Erreur lors de la sauvegarde de ClasseEntity: " + e.getMessage());
            return false;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    /**
     * Surcharge de la méthode update pour gérer les entités SectorEntity détachées.
     * Avant de mettre à jour la ClasseEntity, on s'assure que la SectorEntity associée
     * est dans un état géré par la session Hibernate actuelle.
     *
     * @param classeEntity L'entité ClasseEntity à mettre à jour.
     * @return true si la mise à jour est réussie, false sinon.
     */
    @Override
    public boolean update(ClasseEntity classeEntity) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            // Si la classeEntity a une SectorEntity associée, nous la fusionnons d'abord
            // pour s'assurer qu'elle est dans un état géré par cette session.
            if (classeEntity.getSector() != null) {
                // merge() retourne une instance gérée de l'entité.
                SectorEntity managedSector = (SectorEntity) session.merge(classeEntity.getSector());
                classeEntity.setSector(managedSector); // Met à jour la ClasseEntity avec la SectorEntity gérée
            }

            session.merge(classeEntity); // Utilise merge pour les mises à jour d'entités existantes
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            System.err.println("Erreur lors de la mise à jour de ClasseEntity: " + e.getMessage());
            return false;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    /**
     * Surcharge de la méthode list() pour charger toutes les entités ClasseEntity
     * avec leur SectorEntity associée en utilisant JOIN FETCH.
     * Ceci permet d'éviter les LazyInitializationException.
     *
     * @return La liste de toutes les entités ClasseEntity avec leurs secteurs chargés.
     */
    @Override
    public List<ClasseEntity> list() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<ClasseEntity> cq = cb.createQuery(ClasseEntity.class);
            Root<ClasseEntity> root = cq.from(ClasseEntity.class);

            // CHANGEMENT IMPORTANT : Utilisation de root.fetch pour charger l'entité sector
            // Cela résout le problème de LazyInitializationException pour la liste complète
            root.fetch("sector", JoinType.INNER);

            cq.select(root);
            return session.createQuery(cq).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Erreur lors de la récupération de la liste des ClasseEntity: " + e.getMessage());
            return List.of(); // Retourne une liste vide en cas d'erreur
        }
    }

    /**
     * Surcharge de la méthode get(id) pour charger une entité ClasseEntity
     * avec sa SectorEntity associée en utilisant JOIN FETCH.
     * Ceci permet d'éviter les LazyInitializationException.
     *
     * @param id L'ID de l'entité ClasseEntity à récupérer.
     * @return L'entité ClasseEntity si trouvée, sinon null.
     */
    @Override
    public ClasseEntity get(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<ClasseEntity> cq = cb.createQuery(ClasseEntity.class);
            Root<ClasseEntity> root = cq.from(ClasseEntity.class);

            // CHANGEMENT IMPORTANT : Utilisation de root.fetch pour charger l'entité sector
            // Cela résout le problème de LazyInitializationException pour une entité unique
            root.fetch("sector", JoinType.INNER);

            Predicate predicate = cb.equal(root.get("id"), id);
            cq.select(root).where(predicate);

            return session.createQuery(cq).uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Erreur lors de la récupération de ClasseEntity par ID: " + e.getMessage());
            return null; // Retourne null en cas d'erreur
        }
    }

    /**
     * Recherche une classe par son nom.
     * J'ai renommé la méthode pour plus de clarté, car elle recherche par "className" et non par "code".
     *
     * @param className Le nom de la classe à rechercher.
     * @return Un Optional contenant l'entité ClasseEntity si trouvée, sinon un Optional vide.
     */
    @Override
    public Optional<ClasseEntity> findByClassName(String className) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<ClasseEntity> cq = cb.createQuery(ClasseEntity.class);
            Root<ClasseEntity> root = cq.from(ClasseEntity.class);

            // Il est bon de charger le secteur ici aussi si vous avez l'intention d'y accéder.
            root.fetch("sector", JoinType.INNER);

            Predicate predicate = cb.equal(root.get("className"), className);
            cq.select(root).where(predicate);

            return Optional.ofNullable(session.createQuery(cq).uniqueResult());
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Erreur lors de la recherche de ClasseEntity par nom: " + e.getMessage());
            return Optional.empty();
        }
    }

    /**
     * Recherche la liste des classes appartenant à un secteur donné.
     * Changement important : Utilisation de JOIN FETCH pour résoudre l'erreur LazyInitializationException.
     * Le JOIN FETCH permet de charger l'entité SectorEntity en même temps que ClasseEntity,
     * ce qui évite l'exception lorsque la session est fermée.
     *
     * @param sectorId L'ID du secteur pour lequel on veut lister les classes.
     * @return La liste des entités ClasseEntity trouvées.
     */
    @Override
    public List<ClasseEntity> findBySectorId(int sectorId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<ClasseEntity> cq = cb.createQuery(ClasseEntity.class);
            Root<ClasseEntity> root = cq.from(ClasseEntity.class);

            // CHANGEMENT IMPORTANT : Utilisation de root.fetch pour charger l'entité sector
            // Cela résout le problème de LazyInitializationException
            root.fetch("sector", JoinType.INNER);

            Predicate predicate = cb.equal(root.get("sector").get("id"), sectorId);
            cq.select(root).where(predicate);

            return session.createQuery(cq).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Erreur lors de la recherche des ClasseEntity par ID de secteur: " + e.getMessage());
            return List.of();
        }
    }
}
