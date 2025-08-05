package com.groupeisi.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "classes")
public class ClasseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // Changement n°1 : Utiliser Integer au lieu de int

    @Column(name = "class_name", nullable = false, length = 150) // Ajout de la longueur pour la colonne
    private String className;

    @Column(name = "description", length = 500)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sector_id", nullable = false)
    private SectorEntity sector;

    public ClasseEntity() {
    }

    public ClasseEntity(Integer id, String className, String description, SectorEntity sector) {
        this.id = id;
        this.className = className;
        this.description = description;
        this.sector = sector;
    }

    // Getters and setters (inchangés, ils sont corrects)
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getClassName() { return className; }
    public void setClassName(String className) { this.className = className; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public SectorEntity getSector() { return sector; }
    public void setSector(SectorEntity sector) { this.sector = sector; }

    /**
     * Changement n°2 : Implémentation des méthodes equals() et hashCode().
     * C'est essentiel pour le bon fonctionnement des entités JPA, notamment
     * lorsqu'elles sont utilisées dans des collections (Sets, Maps) ou
     * pour gérer les relations bidirectionnelles.
     * La comparaison se base sur la clé primaire 'id'.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClasseEntity that = (ClasseEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
