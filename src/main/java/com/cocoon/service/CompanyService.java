package com.cocoon.service;

import com.cocoon.dto.CompanyDTO;
import com.cocoon.entity.Company;

import java.util.List;

public interface CompanyService {

    List<CompanyDTO> getAllCompanies();
    CompanyDTO save(Company company);

}
