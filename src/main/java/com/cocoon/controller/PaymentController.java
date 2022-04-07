package com.cocoon.controller;

import com.cocoon.dto.PaymentDTO;
import com.cocoon.exception.CocoonException;
import com.cocoon.service.CompanyService;
import com.cocoon.service.InstitutionService;
import com.cocoon.service.PaymentService;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Controller
@RequestMapping("/payment")
// todo this one also can be deleted
public class PaymentController {



//    @EventListener(ApplicationReadyEvent.class)
//    public void getInstitutionsAfterStartUp() {
//        System.out.println(institutionService.getInstitutionsAtStartUp());
//    }


    @GetMapping({"/list", "/list/{year}"})
    public String createPayment(@RequestParam(value = "year", required = false) String selectedYear, Model model) {

        return "payment/payment-list";
    }


    @GetMapping("/newpayment/{id}")
    public String selectInstitution(@PathVariable("id") Long id, Model model) {

        return "payment/payment-method";
    }

    @PostMapping("/newpayment/{id}")
    public String selectInstitutionPost(@PathVariable("id") Long id, PaymentDTO paymentDTO) throws URISyntaxException, IOException {

        return "redirect:/payment/list";
    }

    // To invoice

    @GetMapping("/toInvoice/{id}")
    public String toInvoice(@PathVariable("id") Long id, Model model) throws CocoonException {


        return "payment/payment-success";
    }


    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("date", new Date());
        model.addAttribute("localDateTime", LocalDateTime.now());
        model.addAttribute("localDate", LocalDate.now());
        model.addAttribute("java8Instant", Instant.now());
    }


}
