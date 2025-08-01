package com.groupeisi.metier;

import com.groupeisi.dto.SectorDto;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

import java.util.List;

@WebService
public interface SectorWebService {

    @WebMethod(operationName = "getSector")
    SectorDto get(@WebParam(name = "idSector") Integer id); // Changé de int à Integer

    @WebMethod(operationName = "allSectors")
    List<SectorDto> all();

    @WebMethod(operationName = "saveSector")
    SectorDto save(@WebParam(name = "sector") SectorDto sectorDto);

    @WebMethod(operationName = "updateSector")
    boolean update(@WebParam(name = "sector") SectorDto sectorDto);

    @WebMethod(operationName = "deleteSector")
    boolean delete(@WebParam(name = "idSector") Integer id); // Changé de int à Integer
}