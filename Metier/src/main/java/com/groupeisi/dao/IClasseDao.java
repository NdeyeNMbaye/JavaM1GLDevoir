package com.groupeisi.dao;

import java.util.List;
import java.util.Optional;

import com.groupeisi.entity.ClasseEntity;

public interface IClasseDao extends Repository<ClasseEntity> {
    Optional<ClasseEntity> findByCode(String code);
    List<ClasseEntity> findBySectorId(int sectorId);
}