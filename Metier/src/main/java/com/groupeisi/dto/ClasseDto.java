package com.groupeisi.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Objects;

/**
 * Data Transfer Object (DTO) pour l'entité ClasseEntity.
 * Cette classe est utilisée pour transférer des données entre les différentes
 * couches de l'application, en particulier pour la sérialisation/désérialisation
 * en XML grâce aux annotations JAXB.
 */
@XmlRootElement(name = "classe")
@XmlAccessorType(XmlAccessType.FIELD)
public class ClasseDto implements Serializable {

    @XmlElement(name = "idClasse")
    private Integer id;

    @XmlElement(name = "className", required = true)
    private String className;

    @XmlElement(name = "description", required = true)
    private String description;

    @XmlElement(name = "idSector", required = true)
    private Integer idSector;

    @XmlElement(name = "sectorName")
    private String sectorName;

    /**
     * Constructeur par défaut.
     */
    public ClasseDto() {
        super();
    }

    /**
     * Constructeur avec tous les champs.
     * @param id L'identifiant de la classe.
     * @param className Le nom de la classe.
     * @param description La description de la classe.
     * @param idSector L'identifiant du secteur associé.
     * @param sectorName Le nom du secteur associé.
     */
    public ClasseDto(Integer id, String className, String description, Integer idSector, String sectorName) {
        this.id = id;
        this.className = className;
        this.description = description;
        this.idSector = idSector;
        this.sectorName = sectorName;
    }

    // Getters
    public Integer getId() {
        return id;
    }

    public String getClassName() {
        return className;
    }

    public String getDescription() {
        return description;
    }

    public Integer getIdSector() {
        return idSector;
    }

    public String getSectorName() {
        return sectorName;
    }

    // Setters
    public void setId(Integer id) {
        this.id = id;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIdSector(Integer idSector) {
        this.idSector = idSector;
    }

    public void setSectorName(String sectorName) {
        this.sectorName = sectorName;
    }

    /**
     * Crée une représentation textuelle de l'objet, utile pour le débogage et la journalisation.
     * @return La représentation String de l'objet ClasseDto.
     */
    @Override
    public String toString() {
        return "ClasseDto{" +
                "id=" + id +
                ", className='" + className + '\'' +
                ", description='" + description + '\'' +
                ", idSector=" + idSector +
                ", sectorName='" + sectorName + '\'' +
                '}';
    }

    /**
     * Compare l'objet courant à un autre objet pour vérifier s'ils sont égaux.
     * @param o L'objet à comparer.
     * @return true si les objets sont égaux, false sinon.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClasseDto classeDto = (ClasseDto) o;
        return Objects.equals(id, classeDto.id) &&
                Objects.equals(className, classeDto.className) &&
                Objects.equals(description, classeDto.description) &&
                Objects.equals(idSector, classeDto.idSector) &&
                Objects.equals(sectorName, classeDto.sectorName);
    }

    /**
     * Génère un code de hachage pour l'objet, ce qui est nécessaire pour l'utilisation
     * dans des collections basées sur le hachage (comme HashMap ou HashSet).
     * @return Le code de hachage de l'objet.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, className, description, idSector, sectorName);
    }
}
