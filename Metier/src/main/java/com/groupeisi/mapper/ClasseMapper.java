package com.groupeisi.mapper;

import com.groupeisi.dto.ClasseDto;
import com.groupeisi.entity.ClasseEntity;

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
        // Les deux méthodes getId() renvoient maintenant un type compatible
        dto.setId(classe.getId());
        dto.setClassName(classe.getClassName());
        dto.setDescription(classe.getDescription());

        return dto;
    }

    public static ClasseEntity toClasseEntity(ClasseDto dto) {
        if (dto == null) return null;

        ClasseEntity classe = new ClasseEntity();
        // Les deux méthodes getId() renvoient maintenant un type compatible
        classe.setId(dto.getId());
        classe.setClassName(dto.getClassName());
        classe.setDescription(dto.getDescription());

        return classe;
    }
}