package com.cocoon.controller;

import com.cocoon.client.CurrencyClient;
import com.cocoon.dto.currency.Rate;
import com.cocoon.entity.jpa_customization.IInvoiceForDashBoard;
import com.cocoon.service.CompanyService;
import com.cocoon.service.InvoiceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    private  final CompanyService companyService;
    private final InvoiceService invoiceService;
    private final CurrencyClient currencyClient;

    public DashboardController(CompanyService companyService,
                               InvoiceService invoiceService,
                               CurrencyClient currencyClient) {
        this.companyService = companyService;
        this.invoiceService = invoiceService;
        this.currencyClient = currencyClient;
    }

    @GetMapping()
    public String invoiceTopThreeList(Model model){
        //added by kicchi to add the current currency rates
        model.addAttribute("rates", getExchangeRates());

        List<IInvoiceForDashBoard> updatedInvoices = invoiceService.getDashboardInvoiceTop3(companyService.getCompanyByLoggedInUser().getId());
        model.addAttribute("invoices", updatedInvoices);
        model.addAttribute("result", invoiceService.calculateTotalProfitLoss());
        return "dashboard";
    }

    //getting the current exchange rates
    //http://data.fixer.io/api/latest?access_key=0efa2bc3ea0bb4f378496f560590a648&symbols=USD,GBP,RUB,JPY&format=1
    // todo bussines controller içinde olmamalıdır. third party toollar kullanılacaksa muhakkak mock class ları da yazılmalıdır.
    private Rate getExchangeRates(){
        return currencyClient.retrieveCurrencyDetails().rates;
    }

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("date", new Date());
        model.addAttribute("localDateTime", LocalDateTime.now());
        model.addAttribute("localDate", LocalDate.now());
        model.addAttribute("java8Instant", Instant.now());
    }

}