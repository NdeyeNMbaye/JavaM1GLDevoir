package com.groupeisi.service;

import com.groupeisi.dto.SectorDto;

import java.util.List;

public interface ISectorService {

    List<SectorDto> getAll();

    SectorDto get(int id);

    boolean save(SectorDto sectorDto);

    boolean update(SectorDto sectorDto);

    boolean delete(int id);
}
