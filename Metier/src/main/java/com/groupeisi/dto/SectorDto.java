package com.groupeisi.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "sector")
@XmlAccessorType(XmlAccessType.FIELD)
public class SectorDto implements Serializable {

    @XmlElement(name = "id", required = true)
    private Integer id;

    @XmlElement(name = "name", required = true)
    private String name;

    // Option 1 : on ne met pas la liste des classes dans ce DTO
    // Option 2 : on peut inclure une liste de ClasseDto si tu veux exposer les classes
    // Exemple ci-dessous, tu peux décommenter si besoin

    /*
    @XmlElementWrapper(name = "classes")
    @XmlElement(name = "class")
    private List<ClasseDto> classes;

    public List<ClasseDto> getClasses() {
        return classes;
    }

    public void setClasses(List<ClasseDto> classes) {
        this.classes = classes;
    }
    */

    public SectorDto() {
        super();
    }

    public SectorDto(Integer id, String name /*, List<ClasseDto> classes */) {
        this.id = id;
        this.name = name;
        // this.classes = classes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}