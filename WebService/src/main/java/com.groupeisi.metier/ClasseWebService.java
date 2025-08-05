// Fichier : com/groupeisi/metier/ClasseWebService.java
package com.groupeisi.metier;

import java.util.List;
import com.groupeisi.dto.ClasseDto;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

/**
 * Interface pour le service web gérant les opérations sur les classes.
 * Cette interface utilise les annotations JAX-WS pour définir les méthodes du service web.
 */
@WebService
public interface ClasseWebService {

    /**
     * Récupère une classe par son identifiant.
     * @param id L'identifiant de la classe.
     * @return Le DTO de la classe trouvée, ou null si non trouvée.
     */
    @WebMethod(operationName = "getClasse")
    ClasseDto getClasseById(@WebParam(name = "idClasse") Integer id);

    /**
     * Récupère la liste de toutes les classes.
     * @return Une liste de DTO de classes.
     */
    @WebMethod(operationName = "allClasses")
    List<ClasseDto> all();

    /**
     * Sauvegarde une nouvelle classe.
     *
     * @param classeDto Le DTO de la classe à sauvegarder.
     * @return Le DTO de la classe sauvegardée (avec son ID généré), ou null en cas d'échec.
     */
    @WebMethod(operationName = "saveClasse")
    boolean saveClasse(@WebParam(name = "classe") ClasseDto classeDto);

    /**
     * Met à jour une classe existante.
     *
     * @param classeDto Le DTO de la classe à mettre à jour.
     * @return Le DTO de la classe mise à jour, ou null en cas d'échec.
     */
    @WebMethod(operationName = "updateClasse")
    boolean updateClasse(@WebParam(name = "classe") ClasseDto classeDto);

    /**
     * Supprime une classe par son identifiant.
     *
     * @param id L'identifiant de la classe à supprimer.
     * @return Le DTO de la classe qui a été supprimée, ou null si non trouvée.
     */
    @WebMethod(operationName = "deleteClasse")
    boolean deleteClasseById(@WebParam(name = "idClasse") Integer id);
}
