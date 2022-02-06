package com.cocoon.implementation;

import com.cocoon.dto.CompanyDTO;
import com.cocoon.entity.Company;
import com.cocoon.repository.CompanyRepo;
import com.cocoon.service.CompanyService;
import com.cocoon.util.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepo companyRepo;
    @Autowired
    private MapperUtil mapperUtil;

    @Override
    public List<CompanyDTO> getAllCompanies() {
        List<Company> companyList = companyRepo.findAll();
        return companyList.stream().map(company ->
                mapperUtil.convert(company, new CompanyDTO())).collect(Collectors.toList());
    }

    @Override
    public CompanyDTO save(CompanyDTO companyDTO) {
        Company savedCompany = companyRepo.save(mapperUtil.convert(companyDTO, new Company()));
        return mapperUtil.convert(savedCompany, new CompanyDTO());
    }
}
