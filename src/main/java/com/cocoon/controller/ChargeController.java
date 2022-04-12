package com.cocoon.controller;

import com.cocoon.dto.PaymentDTO;
import com.cocoon.entity.Payment;
import com.cocoon.entity.common.ChargeRequest;
import com.cocoon.implementation.StripeServiceImpl;
import com.cocoon.service.CompanyService;
import com.cocoon.service.PaymentService;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/charge")
public class ChargeController {


    private final StripeServiceImpl stripeServiceImpl;
    private final PaymentService paymentService;
    private final CompanyService companyService;

    public ChargeController(StripeServiceImpl stripeServiceImpl, PaymentService paymentService, CompanyService companyService) {
        this.stripeServiceImpl = stripeServiceImpl;
        this.paymentService = paymentService;
        this.companyService = companyService;
    }

    @PostMapping("/{id}")
    public String charge(ChargeRequest chargeRequest, @PathVariable("id") Long id, Model model)
            throws StripeException {
        chargeRequest.setDescription("Example charge");
        chargeRequest.setCurrency(ChargeRequest.Currency.EUR);
        Charge charge = stripeServiceImpl.charge(chargeRequest);
        PaymentDTO dto = paymentService.updatePayment(id);
        model.addAttribute("id", charge.getId());
        model.addAttribute("status", charge.getStatus());
        model.addAttribute("chargeId", charge.getId());
        model.addAttribute("balance_transaction", charge.getBalanceTransaction());
        model.addAttribute("company", companyService.getCompanyByLoggedInUser());
        model.addAttribute("payment", dto);
        return "payment/payment-success";
    }

    @ExceptionHandler(StripeException.class)
    public String handleError(Model model, StripeException ex) {
        model.addAttribute("error", ex.getMessage());
        return "payment/payment-success";
    }
}