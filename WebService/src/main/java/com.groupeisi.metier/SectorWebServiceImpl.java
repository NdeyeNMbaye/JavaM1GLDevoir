package com.groupeisi.metier;

import java.util.List;

import com.groupeisi.dto.SectorDto;
import com.groupeisi.entity.SectorEntity;
import com.groupeisi.mapper.SectorMapper;
import com.groupeisi.service.ISectorService;
import com.groupeisi.service.SectorService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

@WebService(endpointInterface = "com.groupeisi.metier.SectorWebService")
public class SectorWebServiceImpl implements SectorWebService {

    private final ISectorService sectorService = new SectorService();

    @Override
    @WebMethod(operationName = "getSector")
    public SectorDto get(@WebParam(name = "idSector") Integer id) {
        // La méthode get() du service retourne une entité. Nous devons la mapper en DTO.
        SectorEntity sectorEntity = sectorService.get(id);
        return SectorMapper.toSectorDto(sectorEntity);
    }

    @Override
    @WebMethod(operationName = "allSectors")
    public List<SectorDto> all() {
        return sectorService.getAll();
    }

    @Override
    @WebMethod(operationName = "saveSector")
    public SectorDto save(@WebParam(name = "sector") SectorDto sectorDto) {
        return sectorService.save(sectorDto) ? sectorDto : null;
    }

    @Override
    @WebMethod(operationName = "updateSector")
    public boolean update(@WebParam(name = "sector") SectorDto sectorDto) {
        return sectorService.update(sectorDto);
    }

    @Override
    @WebMethod(operationName = "deleteSector")
    public boolean delete(@WebParam(name = "idSector") Integer id) {
        return sectorService.delete(id);
    }
}