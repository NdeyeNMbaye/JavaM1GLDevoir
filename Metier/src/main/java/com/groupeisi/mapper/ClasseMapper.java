package com.groupeisi.mapper;

import com.groupeisi.dto.ClasseDto;
import com.groupeisi.entity.ClasseEntity;
import com.groupeisi.entity.SectorEntity;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe utilitaire pour la conversion entre les objets
 * ClasseEntity (entité de persistance) et ClasseDto (objet de transfert de données).
 */
public class ClasseMapper {

    /**
     * Convertit une liste d'objets ClasseEntity en une liste d'objets ClasseDto.
     *
     * @param classes La liste de ClasseEntity à convertir.
     * @return Une liste de ClasseDto.
     */
    public static List<ClasseDto> listClasseEntityToListClasseDto(List<ClasseEntity> classes) {
        if (classes == null) {
            return null;
        }
        return classes.stream()
                .map(ClasseMapper::toClasseDto)
                .collect(Collectors.toList());
    }

    /**
     * Convertit un objet ClasseEntity en un objet ClasseDto.
     *
     * @param classe L'entité ClasseEntity à convertir.
     * @return Un objet ClasseDto correspondant, ou null si l'entité est null.
     */
    public static ClasseDto toClasseDto(ClasseEntity classe) {
        if (classe == null) {
            return null;
        }

        ClasseDto dto = new ClasseDto();
        dto.setId(classe.getId());
        dto.setClassName(classe.getClassName());
        dto.setDescription(classe.getDescription());

        // Gérer la relation avec l'entité Sector
        if (classe.getSector() != null) {
            dto.setIdSector(classe.getSector().getId());
            dto.setSectorName(classe.getSector().getName());
        }

        return dto;
    }

    /**
     * Convertit un objet ClasseDto en un objet ClasseEntity.
     *
     * @param dto L'objet ClasseDto à convertir.
     * @return Une entité ClasseEntity correspondante, ou null si le DTO est null.
     */
    public static ClasseEntity toClasseEntity(ClasseDto dto) {
        if (dto == null) {
            return null;
        }

        ClasseEntity classe = new ClasseEntity();
        classe.setId(dto.getId());
        classe.setClassName(dto.getClassName());
        classe.setDescription(dto.getDescription());

        // Important : La liaison du Sector sera complétée par la classe de service.
        // Ici, nous créons seulement un SectorEntity avec l'ID pour minimiser les
        // opérations de base de données à ce niveau.
        SectorEntity sector = new SectorEntity();
        sector.setId(dto.getIdSector());
        classe.setSector(sector);

        return classe;
    }
}
