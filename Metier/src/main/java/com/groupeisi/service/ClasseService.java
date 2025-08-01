package com.groupeisi.service;

import com.groupeisi.dao.ClasseDao;
import com.groupeisi.dao.IClasseDao;
import com.groupeisi.dto.ClasseDto;
import com.groupeisi.entity.ClasseEntity;
import com.groupeisi.mapper.ClasseMapper;

import java.util.List;
import java.util.stream.Collectors;

public class ClasseService implements IClasseService {

    private final IClasseDao classeDao = new ClasseDao();
    // Votre commentaire est correct, on n'a plus besoin d'instancier un mapper si ses méthodes sont statiques.

    @Override
    public List<ClasseDto> getAll() {
        return classeDao.list().stream()
                // On appelle la méthode statique directement
                .map(ClasseMapper::toClasseDto)
                .collect(Collectors.toList());
    }

    @Override
    public ClasseDto get(int id) {
        ClasseEntity entity = classeDao.get(id);
        // On appelle la méthode statique directement
        return (entity != null) ? ClasseMapper.toClasseDto(entity) : null;
    }

    @Override
    public boolean save(ClasseDto classeDto) {
        // On appelle la méthode statique directement
        return classeDao.save(ClasseMapper.toClasseEntity(classeDto));
    }

    @Override
    public boolean update(ClasseDto classeDto) {
        // On appelle la méthode statique directement
        return classeDao.update(ClasseMapper.toClasseEntity(classeDto));
    }

    @Override
    public boolean delete(int id) {
        return classeDao.delete(id);
    }
}