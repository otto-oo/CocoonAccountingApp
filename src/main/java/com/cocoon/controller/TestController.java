package com.cocoon.controller;

import com.cocoon.repository.TestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

    @Autowired
    TestRepo testRepo;


    @RequestMapping(value = {"/test"})
    public String test(){

        return testRepo.getById(0).getTestval();
    }
}
