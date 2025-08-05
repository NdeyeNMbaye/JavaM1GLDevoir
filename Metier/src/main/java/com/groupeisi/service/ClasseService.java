package com.groupeisi.service;

import com.groupeisi.dao.ClasseDao;
import com.groupeisi.dao.IClasseDao;
import com.groupeisi.dto.ClasseDto;
import com.groupeisi.entity.ClasseEntity;
import com.groupeisi.entity.SectorEntity;
import com.groupeisi.mapper.ClasseMapper;

import java.util.List;
import java.util.Optional;

public class ClasseService implements IClasseService {

    private final IClasseDao classeDao = new ClasseDao();
    private final ISectorService sectorService = new SectorService(); // Utilise le service pour obtenir le secteur

    @Override
    public List<ClasseDto> getAll() {
        return ClasseMapper.listClasseEntityToListClasseDto(classeDao.list());
    }

    @Override
    public ClasseDto get(int id) {
        // La méthode get dans le DAO retourne une entité, nous la mappons en DTO ici
        ClasseEntity classeEntity = classeDao.get(id);
        return ClasseMapper.toClasseDto(classeEntity);
    }

    @Override
    public boolean save(ClasseDto classeDto) {
        ClasseEntity classeEntity = ClasseMapper.toClasseEntity(classeDto);
        // Récupère la SectorEntity via le service. Elle sera potentiellement "détachée".
        // La logique de "merge" sera gérée dans ClasseDao.save()
        SectorEntity sector = sectorService.get(classeDto.getIdSector());
        classeEntity.setSector(sector);
        return classeDao.save(classeEntity);
    }

    @Override
    public boolean update(ClasseDto classeDto) {
        ClasseEntity classeEntity = ClasseMapper.toClasseEntity(classeDto);
        // Récupère la SectorEntity via le service. Elle sera potentiellement "détachée".
        // La logique de "merge" sera gérée dans ClasseDao.update()
        SectorEntity sector = sectorService.get(classeDto.getIdSector());
        classeEntity.setSector(sector);
        return classeDao.update(classeEntity);
    }

    @Override
    public boolean delete(int id) {
        return classeDao.delete(id);
    }

    @Override
    public Optional<ClasseDto> findByClassName(String className) {
        Optional<ClasseEntity> classeEntity = classeDao.findByClassName(className);
        return classeEntity.map(ClasseMapper::toClasseDto);
    }

    @Override
    public List<ClasseDto> findBySectorId(int sectorId) {
        List<ClasseEntity> classeEntities = classeDao.findBySectorId(sectorId);
        return ClasseMapper.listClasseEntityToListClasseDto(classeEntities);
    }
}
