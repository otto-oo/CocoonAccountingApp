package com.cocoon.controller;

import com.cocoon.dto.CompanyDTO;
import com.cocoon.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @PostMapping("/create")
    public String saveCompany(CompanyDTO companyDTO){
        companyService.save(companyDTO)
    }

}
