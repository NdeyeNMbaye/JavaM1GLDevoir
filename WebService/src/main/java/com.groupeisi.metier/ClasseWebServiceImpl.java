package com.groupeisi.metier;

import java.util.List;

import com.groupeisi.dto.ClasseDto;
import com.groupeisi.service.IClasseService;
import com.groupeisi.service.ClasseService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

@WebService(endpointInterface = "com.groupeisi.metier.ClasseWebService")
public class ClasseWebServiceImpl implements ClasseWebService {

    private final IClasseService classeService = new ClasseService();

    @Override
    @WebMethod(operationName = "getClasse")
    public ClasseDto getClasseById(@WebParam(name = "idClasse") Integer id) {
        return classeService.get(id);
    }

    @Override
    @WebMethod(operationName = "allClasses")
    public List<ClasseDto> all() {
        return classeService.getAll();
    }

    @Override
    @WebMethod(operationName = "saveClasse")
    public ClasseDto saveClasse(@WebParam(name = "classe") ClasseDto classeDto) {
        return classeService.save(classeDto) ? classeDto : null;
    }

    @Override
    @WebMethod(operationName = "updateClasse")
    public boolean updateClasse(@WebParam(name = "classe") ClasseDto classeDto) {
        return classeService.update(classeDto);
    }

    @Override
    @WebMethod(operationName = "deleteClasse")
    public boolean deleteClasseById(@WebParam(name = "idClasse") Integer id) {
        return classeService.delete(id);
    }
}