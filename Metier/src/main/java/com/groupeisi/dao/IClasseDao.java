package com.groupeisi.dao;

import com.groupeisi.entity.ClasseEntity;

import java.util.List;
import java.util.Optional;

/**
 * Cette interface définit les méthodes spécifiques à l'entité ClasseEntity.
 * Elle étend l'interface générique Repository pour hériter des opérations CRUD de base.
 */
public interface IClasseDao extends Repository<ClasseEntity> {

    /**
     * Recherche une entité ClasseEntity par son nom de classe.
     * Cette méthode remplace 'findByCode' pour une meilleure clarté du code.
     *
     * @param className Le nom de la classe à rechercher.
     * @return Un objet Optional contenant l'entité ClasseEntity si elle est trouvée,
     * ou un Optional vide sinon.
     */
    Optional<ClasseEntity> findByClassName(String className);

    /**
     * Recherche la liste de toutes les classes appartenant à un secteur spécifique.
     *
     * @param sectorId L'identifiant du secteur pour lequel les classes doivent être recherchées.
     * @return Une liste de ClasseEntity correspondant au secteur donné.
     */
    List<ClasseEntity> findBySectorId(int sectorId);
}
