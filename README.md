Devoir M1GL - Gestion des Classes et Secteurs
Description du Projet
Ce projet est un service web SOAP développé en Java, conçu pour la gestion des entités Classe et Secteur. Il utilise Hibernate pour la persistance des données et expose ses fonctionnalités via des endpoints SOAP. L'objectif principal est de fournir une API pour les opérations CRUD (Create, Read, Update, Delete) sur les classes et les secteurs.

Note : Le service est entièrement fonctionnel et a été testé avec succès via SoapUI.

Fonctionnalités
Le service web implémente les opérations de base (CRUD) pour les ressources Classe et Secteur :
Pour la ressource Classe (via ClasseWebService) :
allClasses : Récupérer toutes les classes.

saveClasse : Ajouter une nouvelle classe.

updateClasse : Modifier une classe existante.

getClasse : Récupérer une classe par son identifiant.

deleteClasse : Supprimer une classe.

Pour la ressource Secteur (via SectorWebService) :
allSectors : Récupérer tous les secteurs.

saveSector : Ajouter un nouveau secteur.

updateSector : Modifier un secteur existant.

getSector : Récupérer un secteur par son identifiant.

deleteSector : Supprimer un secteur.

Architecture du Projet
Le projet est structuré en deux modules Maven distincts :

Metier (Module Core/Business Logic) :

Contient la logique métier principale et la couche d'accès aux données (DAO).

com.groupeisi.config : Configuration d'Hibernate (e.g., HibernateUtil).

com.groupeisi.dao : Implémentations des Data Access Objects pour interagir avec la base de données (e.g., ClasseDao, SectorDao).

com.groupeisi.dto : Objets de transfert de données (Data Transfer Objects) pour la communication entre les couches.

com.groupeisi.entity : Entités JPA/Hibernate représentant les tables de la base de données (e.g., ClasseEntity, SectorEntity).

com.groupeisi.exception : Classes d'exceptions personnalisées.

com.groupeisi.mapper : Classes pour la conversion entre DTOs et Entités.

com.groupeisi.service : Couche de service qui orchestre les opérations métier en utilisant les DAOs.

pom.xml : Fichier de configuration Maven pour ce module, gérant les dépendances comme Hibernate.

WebService (Module SOAP Web Service) :

Expose les fonctionnalités du module Metier via des services web SOAP.

com.groupeisi.metier : Contient les implémentations des services web SOAP (e.g., ClasseWebServiceImpl, SectorWebServiceImpl). C'est ici que les méthodes exposées via SOAP sont définies.

webapp : Répertoire standard pour les applications web, contenant :

WEB-INF : Fichiers de configuration spécifiques à l'application web (e.g., web.xml et les fichiers WSDL/XSD générés).

index.jsp : Une page JSP d'exemple (peut être utilisée pour une page d'accueil simple ou des informations sur le service).

pom.xml : Fichier de configuration Maven pour ce module, déclarant le module Metier comme dépendance et configurant la génération du service web.
<img width="573" height="934" alt="image" src="https://github.com/user-attachments/assets/b8ed0fcd-7d84-4ad6-967b-7471144bd157" />


Technologies Utilisées
Langage de Programmation : Java (JDK 17)

Framework de Persistance : Hibernate (avec JPA)

Base de Données : PostgreSQL

Serveur d'Applications : Apache Tomcat 10.1.x

Services Web : SOAP (via JAX-WS, implémenté avec des bibliothèques standards Java EE)

Gestion de Projet : Maven

Outil de Test API : SoapUI

Utilisation du Service Web (SOAP)
Une fois le service déployé, vous pourrez y accéder via les URL WSDL. Les URL exactes dépendront de la configuration de votre web.xml et des annotations @WebService dans vos implémentations de service.

Généralement, les URL WSDL seront de la forme :

Service Classe : http://localhost:8082/WebService/ClasseWebService?wsdl

Service Secteur : http://localhost:8082/WebService/SectorWebService?wsdl

(Le port 8082 est un exemple courant pour les applications déployées sur Tomcat.)

Vous pouvez utiliser SoapUI pour tester les méthodes exposées :

Ouvrez SoapUI.

Créez un nouveau projet SOAP en utilisant l'une des URL WSDL ci-dessus.

SoapUI importera les définitions de service et vous permettra d'appeler les opérations disponibles.
 Quelques captures avec l'ajout et la liste 
 -SECTOR
 La liste
 <img width="1844" height="912" alt="image" src="https://github.com/user-attachments/assets/8135c4ce-ceb4-4039-85d9-996bb807a775" />
 L'ajout 
 <img width="1913" height="1002" alt="image" src="https://github.com/user-attachments/assets/06a33876-6717-4297-a94d-13bac05280b3" />
 -CLASSE
 La liste 
 <img width="1906" height="984" alt="image" src="https://github.com/user-attachments/assets/adae801f-755f-415b-9651-607ff35ff203" />
 L'ajout
 <img width="1890" height="867" alt="image" src="https://github.com/user-attachments/assets/80de6f64-7705-4700-ba07-d27f50fa04f6" />



 
