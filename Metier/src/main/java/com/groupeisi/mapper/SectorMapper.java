package com.groupeisi.mapper;

import com.groupeisi.dto.SectorDto;
import com.groupeisi.entity.SectorEntity;

import java.util.List;
import java.util.stream.Collectors;

public class SectorMapper {

    public static List<SectorDto> listSectorEntityToListSectorDto(List<SectorEntity> sectors) {
        return sectors.stream()
                .map(SectorMapper::toSectorDto)
                .collect(Collectors.toList());
    }

    public static SectorDto toSectorDto(SectorEntity sector) {
        if (sector == null) return null;

        SectorDto dto = new SectorDto();
        dto.setId(sector.getId());
        dto.setName(sector.getName());

        // Tu peux ajouter le mapping des classes si besoin (liste de ClasseDto)
        // dto.setClasses(ClasseMapper.listClasseEntityToListClasseDto(sector.getClasses()));

        return dto;
    }

    public static SectorEntity toSectorEntity(SectorDto dto) {
        if (dto == null) return null;

        SectorEntity sector = new SectorEntity();
        sector.setId(dto.getId());
        sector.setName(dto.getName());

        // Pour les classes liées, ça se gère côté service ou DAO si tu veux
        // sector.setClasses(...);

        return sector;
    }
}
