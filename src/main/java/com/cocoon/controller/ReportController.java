package com.cocoon.controller;

import com.cocoon.dto.InvoiceDTO;
import com.cocoon.dto.InvoiceProductDTO;
import com.cocoon.entity.InvoiceProduct;
import com.cocoon.repository.InvoiceProductRepo;
import com.cocoon.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
@RequestMapping("/report")
public class ReportController {


    private ProductService productService;
    private InvoiceService invoiceService;
    private InvoiceProductService invoiceProductService;

    private InvoiceDTO currentInvoiceDTO = new InvoiceDTO();

    @Autowired
    private InvoiceProductRepo invoiceProductRepo;

    private boolean active = true;

    public ReportController(InvoiceService invoiceService, ProductService productService, InvoiceProductService invoiceProductService) {
        this.invoiceService = invoiceService;
        this.productService = productService;
        this.invoiceProductService = invoiceProductService;
    }

    @GetMapping("/stock")
    public String getStock(Model model){

        ArrayList<InvoiceProduct> products = (ArrayList<InvoiceProduct>) invoiceProductRepo.getStockReportList();

        model.addAttribute("stock", new ArrayList<InvoiceProductDTO>());

        return "report/stock-report.html";
    }
}
