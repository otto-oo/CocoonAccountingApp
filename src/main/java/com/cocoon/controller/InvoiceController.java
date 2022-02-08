package com.cocoon.controller;

import com.cocoon.dto.InvoiceDTO;
import com.cocoon.service.InvoiceService;
import com.cocoon.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sales-invoice")
public class InvoiceController {

    InvoiceService invoiceService;
    ProductService productService;

    public InvoiceController(InvoiceService invoiceService, ProductService productService) {
        this.invoiceService = invoiceService;
        this.productService = productService;
    }

    @GetMapping("/list")
    public String invoiceCreate(Model model){

            model.addAttribute("invoices", invoiceService.getAllInvoices());
            model.addAttribute("products", productService.getProductsByInvoiceId(2L));

        return "/invoice/sales-invoice-list";
    }


}
