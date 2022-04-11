package com.cocoon.controller;

import com.cocoon.dto.PaymentDTO;
import com.cocoon.exception.CocoonException;
import com.cocoon.service.CompanyService;
import com.cocoon.service.PaymentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

@Controller
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService paymentService;
    private final CompanyService companyService;

    public PaymentController(PaymentService paymentService, CompanyService companyService) {
        this.paymentService = paymentService;
        this.companyService = companyService;
    }


    @GetMapping({"/list", "/list/{year}"})
    public String createPayment(@RequestParam(value = "year", required = false) String selectedYear, Model model) {

        int selectedYear1 = (selectedYear == null || selectedYear.isEmpty()) ? LocalDate.now().getYear() : Integer.parseInt(selectedYear);
        paymentService.createPaymentsIfNotExist(selectedYear1);
        model.addAttribute("payments",paymentService.getAllPaymentsByYear(selectedYear1));
        model.addAttribute("year", selectedYear1);
        return "payment/payment-list";
    }


    @GetMapping("/newpayment/{id}")
    public String selectInstitution(@PathVariable("id") Long id, Model model) {

        model.addAttribute("payment", paymentService.getPaymentById(id));

        return "payment/payment-method";
    }

    @PostMapping("/newpayment/{id}")
    public String selectInstitutionPost(@PathVariable("id") Long id, PaymentDTO paymentDTO) {

        PaymentDTO convertedPaymentDto = paymentService.getPaymentById(id);
        paymentService.updatePayment(convertedPaymentDto);

        return "redirect:/payment/list";
    }

    // To invoice

    @GetMapping("/toInvoice/{id}")
    public String toInvoice(@PathVariable("id") Long id, Model model) throws CocoonException {

        model.addAttribute("payment", paymentService.getPaymentById(id));
        model.addAttribute("company", companyService.getCompanyByLoggedInUser());

        return "payment/payment-success";
    }


}