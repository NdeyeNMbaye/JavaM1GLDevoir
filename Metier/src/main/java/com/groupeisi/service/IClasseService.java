package com.groupeisi.service;

import java.util.List;
import java.util.Optional;

import com.groupeisi.dto.ClasseDto;

public interface IClasseService {
    List<ClasseDto> getAll();
    ClasseDto get(int id);
    boolean delete(int id);
    boolean save(ClasseDto classeDto);
    boolean update(ClasseDto classeDto);

    // Méthodes spécifiques (optionnel)
    // Optional<ClasseDto> findByCode(String code);
}
