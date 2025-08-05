// Fichier : com/groupeisi/metier/impl/ClasseWebServiceImpl.java
package com.groupeisi.metier;

import com.groupeisi.dto.ClasseDto;
import com.groupeisi.metier.ClasseWebService;
import com.groupeisi.service.IClasseService;
import com.groupeisi.service.ClasseService; // Supposons que ClasseService implémente IClasseService
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import java.util.List;

/**
 * Implémentation du service web ClasseWebService.
 * Cette classe expose les opérations CRUD pour les classes via des appels de services web.
 */
@WebService(endpointInterface = "com.groupeisi.metier.ClasseWebService")
public class ClasseWebServiceImpl implements ClasseWebService {

    // Instance de la couche service.
    // L'utilisation d'une interface (IClasseService) favorise la flexibilité.
    private IClasseService classeService;

    /**
     * Constructeur par défaut.
     * Dans un contexte réel, l'injection de dépendances serait utilisée pour instancier le service.
     */
    public ClasseWebServiceImpl() {
        // Initialisation directe pour cet exemple.
        // En production, un framework comme Spring gèrerait cette instance.
        this.classeService = new ClasseService();
    }

    /**
     * Récupère un DTO de classe par son identifiant.
     * @param id L'identifiant de la classe à récupérer.
     * @return Le DTO de la classe trouvée.
     */
    @Override
    @WebMethod(operationName = "getClasse")
    public ClasseDto getClasseById(@WebParam(name = "idClasse") Integer id) {
        return classeService.get(id);
    }

    /**
     * Récupère la liste de tous les DTO de classes.
     * @return Une liste de ClasseDto.
     */
    @Override
    @WebMethod(operationName = "allClasses")
    public List<ClasseDto> all() {
        return classeService.getAll();
    }

    /**
     * Sauvegarde une nouvelle classe et renvoie son DTO mis à jour (avec l'ID).
     *
     * @param classeDto Le DTO de la classe à sauvegarder.
     * @return Le DTO de la classe avec son ID généré, ou null si la sauvegarde échoue.
     */
    @Override
    @WebMethod(operationName = "saveClasse")
    public boolean saveClasse(@WebParam(name = "classe") ClasseDto classeDto) {
        // La couche de service est censée retourner le DTO avec l'ID généré
        // pour respecter la signature de la méthode de l'interface.
        return classeService.save(classeDto);
    }

    /**
     * Met à jour une classe existante.
     *
     * @param classeDto Le DTO de la classe à mettre à jour.
     * @return Le DTO mis à jour, ou null en cas d'échec.
     */
    @Override
    @WebMethod(operationName = "updateClasse")
    public boolean updateClasse(@WebParam(name = "classe") ClasseDto classeDto) {
        // La couche de service est censée retourner le DTO mis à jour.
        return classeService.update(classeDto);
    }

    /**
     * Supprime une classe par son identifiant.
     *
     * @param id L'identifiant de la classe à supprimer.
     * @return Le DTO de la classe qui a été supprimée, ou null si non trouvée.
     */
    @Override
    @WebMethod(operationName = "deleteClasse")
    public boolean deleteClasseById(@WebParam(name = "idClasse") Integer id) {
        // La couche de service est censée retourner le DTO de l'objet supprimé.
        return classeService.delete(id);
    }
}
