package com.groupeisi.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "classe") // Ajout du nom de l'élément racine
@XmlAccessorType(XmlAccessType.FIELD)
public class ClasseDto implements Serializable {

    @XmlElement(name = "idClasse", required = true)
    private Integer id;

    @XmlElement(name = "className", required = true)
    private String className;

    @XmlElement(name = "description", required = true)
    private String description;

    @XmlElement(name = "idSector", required = true) // C'est l'ajout crucial
    private Integer idSector;

    public ClasseDto() {
        super();
    }

    public ClasseDto(Integer id, String className, String description, Integer idSector) {
        super();
        this.id = id;
        this.className = className;
        this.description = description;
        this.idSector = idSector;
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
}