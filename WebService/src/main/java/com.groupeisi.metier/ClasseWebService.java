package com.groupeisi.metier;

import java.util.List;

import com.groupeisi.dto.ClasseDto;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

@WebService
public interface ClasseWebService {

    @WebMethod(operationName = "getClasse")
    ClasseDto getClasseById(@WebParam(name = "idClasse") Integer id);

    @WebMethod(operationName = "allClasses")
    List<ClasseDto> all();

    @WebMethod(operationName = "saveClasse")
    ClasseDto saveClasse(@WebParam(name = "classe") ClasseDto classeDto);

    @WebMethod(operationName = "updateClasse")
    boolean updateClasse(@WebParam(name = "classe") ClasseDto classeDto);

    @WebMethod(operationName = "deleteClasse")
    boolean deleteClasseById(@WebParam(name = "idClasse") Integer id);
}