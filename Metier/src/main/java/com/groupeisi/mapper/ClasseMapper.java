package com.groupeisi.mapper;

import com.groupeisi.dto.ClasseDto;
import com.groupeisi.entity.ClasseEntity;
import com.groupeisi.entity.SectorEntity;

import java.util.List;
import java.util.stream.Collectors;

public class ClasseMapper {

    public static List<ClasseDto> listClasseEntityToListClasseDto(List<ClasseEntity> classes) {
        return classes.stream()
                .map(ClasseMapper::toClasseDto)
                .collect(Collectors.toList());
    }

    public static ClasseDto toClasseDto(ClasseEntity classe) {
        if (classe == null) return null;

        ClasseDto dto = new ClasseDto();
        dto.setId(classe.getId());
        dto.setClassName(classe.getClassName());
        dto.setDescription(classe.getDescription());

        // Correction pour le lazy loading
        if (classe.getSector() != null) {
            dto.setSectorName(classe.getSector().getName());
        }

        return dto;
    }

    public static ClasseEntity toClasseEntity(ClasseDto dto) {
        if (dto == null) return null;

        ClasseEntity classe = new ClasseEntity();
        classe.setId(dto.getId());
        classe.setClassName(dto.getClassName());
        classe.setDescription(dto.getDescription());

        // La liaison du Sector sera faite dans la classe de service
        SectorEntity sector = new SectorEntity();
        sector.setId(dto.getIdSector());
        classe.setSector(sector);

        return classe;
    }
}