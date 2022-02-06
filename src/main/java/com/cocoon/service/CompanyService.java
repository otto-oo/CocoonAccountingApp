package com.cocoon.service;

import com.cocoon.dto.CompanyDTO;
import com.cocoon.entity.Company;
import com.cocoon.exception.CocoonException;

import java.util.List;

public interface CompanyService {

    List<CompanyDTO> getAllCompanies();
    CompanyDTO save(CompanyDTO company) throws CocoonException;

}
