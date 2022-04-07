package com.cocoon.service;

import com.cocoon.dto.InstitutionDTO;

import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Stream;

// todo yapily bussiness can be removed
public interface InstitutionService {

    List<InstitutionDTO> getAllInstitutions();

    InstitutionDTO getInstitutionById(String id);

    List<InstitutionDTO> saveIfNotExist(List<String> names);

    InstitutionDTO save(InstitutionDTO institutionDTO);

    List<String> getInstitutionsAtStartUp();

    <T> List<T> getInstitutionsFromApi();


}
