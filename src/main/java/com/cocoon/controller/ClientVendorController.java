package com.cocoon.controller;

import com.cocoon.dto.ClientVendorDTO;
import com.cocoon.exception.CocoonException;
import com.cocoon.repository.StateRepo;
import com.cocoon.service.ClientVendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/client")
public class ClientVendorController {

    @Autowired
    private ClientVendorService clientVendorService;
    @Autowired
    private StateRepo stateRepo;

    @GetMapping("/list")
    public String getClientList(){
        //todo this is the section which belongs ACD-42 ticket @ali
        return "clientvendor/client-vendor-list";
    }

    @GetMapping("/create")
    public String getCreatePage(Model model){
        model.addAttribute("client", new ClientVendorDTO());
        model.addAttribute("states", stateRepo.findAll());

        return "clientvendor/client-vendor-add";
    }

    @PostMapping("/create")
    public String saveClient(ClientVendorDTO clientVendorDTO) throws CocoonException {
        clientVendorService.save(clientVendorDTO);
        return "redirect:/client/list";
    }

}
