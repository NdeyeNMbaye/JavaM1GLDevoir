package com.groupeisi.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

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

    public ClasseDto() {
        super();
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
}