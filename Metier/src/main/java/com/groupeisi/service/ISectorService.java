package com.groupeisi.service;

import com.groupeisi.dto.SectorDto;
import com.groupeisi.entity.SectorEntity; // Import de l'entité

import java.util.List;

public interface ISectorService {

    List<SectorDto> getAll();

    SectorEntity get(int id); // La méthode get() doit retourner une entité

    boolean save(SectorDto sectorDto);

    boolean update(SectorDto sectorDto);

    boolean delete(int id);
}