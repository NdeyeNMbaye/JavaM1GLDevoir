package com.groupeisi.service;

import java.util.List;
import java.util.stream.Collectors;

import com.groupeisi.dao.ClasseDao;
import com.groupeisi.dao.IClasseDao;
import com.groupeisi.dto.ClasseDto;
import com.groupeisi.entity.ClasseEntity;
import com.groupeisi.entity.SectorEntity;
import com.groupeisi.mapper.ClasseMapper;

import com.groupeisi.service.ISectorService; // Import manquant
import com.groupeisi.service.SectorService; // Import manquant

public class ClasseService implements IClasseService {

    private final IClasseDao classeDao = new ClasseDao();
    private final ISectorService sectorService = new SectorService();

    @Override
    public List<ClasseDto> getAll() {
        return classeDao.list().stream()
                .map(ClasseMapper::toClasseDto)
                .collect(Collectors.toList());
    }

    @Override
    public ClasseDto get(int id) {
        ClasseEntity entity = classeDao.get(id);
        return (entity != null) ? ClasseMapper.toClasseDto(entity) : null;
    }

    @Override
    public boolean save(ClasseDto classeDto) {
        SectorEntity sector = sectorService.get(classeDto.getIdSector());

        if (sector != null) {
            ClasseEntity classeEntity = ClasseMapper.toClasseEntity(classeDto);
            classeEntity.setSector(sector);

            return classeDao.save(classeEntity);
        }
        return false;
    }

    @Override
    public boolean update(ClasseDto classeDto) {
        SectorEntity sector = sectorService.get(classeDto.getIdSector());

        if (sector != null) {
            ClasseEntity classeEntity = ClasseMapper.toClasseEntity(classeDto);
            classeEntity.setSector(sector);

            return classeDao.update(classeEntity);
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        return classeDao.delete(id);
    }
}