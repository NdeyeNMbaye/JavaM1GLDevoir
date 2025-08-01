package com.groupeisi.service;

import com.groupeisi.dao.ISectorDao;
import com.groupeisi.dao.SectorDao;
import com.groupeisi.dto.SectorDto;
import com.groupeisi.entity.SectorEntity;
import com.groupeisi.mapper.SectorMapper;

import java.util.List;
import java.util.stream.Collectors;

public class SectorService implements ISectorService {

    private final ISectorDao sectorDao = new SectorDao();

    @Override
    public List<SectorDto> getAll() {
        // La méthode list() n'attend plus d'argument.
        return SectorMapper.listSectorEntityToListSectorDto(sectorDao.list());
    }

    @Override
    public SectorDto get(int id) {
        SectorEntity entity = sectorDao.get(id);
        return (entity != null) ? SectorMapper.toSectorDto(entity) : null;
    }

    @Override
    public boolean save(SectorDto sectorDto) {
        return sectorDao.save(SectorMapper.toSectorEntity(sectorDto));
    }

    @Override
    public boolean update(SectorDto sectorDto) {
        return sectorDao.update(SectorMapper.toSectorEntity(sectorDto));
    }

    @Override
    public boolean delete(int id) {
        return sectorDao.delete(id);
    }
}