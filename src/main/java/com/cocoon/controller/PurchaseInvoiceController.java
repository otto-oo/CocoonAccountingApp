package com.cocoon.controller;

import com.cocoon.dto.ClientDTO;
import com.cocoon.dto.InvoiceDTO;
import com.cocoon.dto.InvoiceProductDTO;
import com.cocoon.enums.CompanyType;
import com.cocoon.enums.InvoiceStatus;
import com.cocoon.enums.InvoiceType;
import com.cocoon.exception.CocoonException;
import com.cocoon.repository.ClientVendorRepo;
import com.cocoon.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/purchase-invoice")
public class PurchaseInvoiceController {

    private InvoiceDTO currentInvoiceDTO = new InvoiceDTO();
    private boolean active = true;

    private final InvoiceService invoiceService;
    private final ProductService productService;
    private final InvoiceProductService invoiceProductService;
    private final ClientVendorService clientVendorService;
    private final ClientVendorRepo clientVendorRepo;
    private final CompanyService companyService;

    public PurchaseInvoiceController(InvoiceService invoiceService, ProductService productService, InvoiceProductService invoiceProductService, ClientVendorService clientVendorService, ClientVendorRepo clientVendorRepo, CompanyService companyService) {
        this.invoiceService = invoiceService;
        this.productService = productService;
        this.invoiceProductService = invoiceProductService;
        this.clientVendorService = clientVendorService;
        this.clientVendorRepo = clientVendorRepo;
        this.companyService = companyService;
    }

    @GetMapping({"/list", "/list/{cancel}"})
    public String invoiceList(@RequestParam(required = false) String cancel, Model model){

        if (cancel != null){
            this.active = true;
        }
        currentInvoiceDTO = new InvoiceDTO();
        List<InvoiceDTO> invoices = invoiceService.getAllInvoicesByCompanyAndType(InvoiceType.PURCHASE);
        List<InvoiceDTO> updatedInvoices = invoices.stream().map(invoiceService::calculateInvoiceCost).collect(Collectors.toList());
        model.addAttribute("invoices", updatedInvoices);
        model.addAttribute("client", new ClientDTO());
        model.addAttribute("invoice", currentInvoiceDTO);

        return "invoice/purchase-invoice-list";
    }

    @GetMapping("/create")
    public String purchaseInvoiceCreate(@RequestParam(required = false) Long id, Model model) throws CocoonException{

        if (id != null){
            currentInvoiceDTO.setClient(clientVendorRepo.getById(id));
        }
        currentInvoiceDTO.setInvoiceNumber(invoiceService.getInvoiceNumber(InvoiceType.PURCHASE));
        currentInvoiceDTO.setInvoiceDate(LocalDate.now());
        model.addAttribute("invoice", currentInvoiceDTO);
        model.addAttribute("invoiceProducts", currentInvoiceDTO.getInvoiceProduct());

        return "invoice/purchase-invoice-create";
    }

    @PostMapping("/create/add-invoice-product")
    public String createInvoiceProduct(InvoiceProductDTO invoiceProductDTO){

        invoiceProductDTO.setName(invoiceProductDTO.getProductDTO().getName());
        currentInvoiceDTO.getInvoiceProduct().add(invoiceProductDTO);
        this.active = false;
        return "redirect:/purchase-invoice/create";
    }

    @PostMapping("/create/delete-invoice-product")
    public String deleteInvoiceProduct(InvoiceProductDTO invoiceProductDTO){

        currentInvoiceDTO.getInvoiceProduct().removeIf(obj -> obj.equals(invoiceProductDTO));
        if (currentInvoiceDTO.getInvoiceProduct().size()==0) this.active = true;
        return "redirect:/purchase-invoice/create";
    }


    @PostMapping("/save-invoice")
    public String createInvoice() throws CocoonException {

        currentInvoiceDTO.setInvoiceType(InvoiceType.PURCHASE);
        InvoiceDTO savedInvoice = invoiceService.save(currentInvoiceDTO);
        invoiceProductService.approveInvoiceProduct(savedInvoice.getId());
        this.active = true;

        return "redirect:/purchase-invoice/list";
    }

    // Update ----------------------------------------------------------------------------------------------------------
    @GetMapping({"/update", "/update/{id}"})
    public String updateInvoice(@PathVariable(value = "id", required = false) Long id, Model model){

        if (id != null) {
            currentInvoiceDTO = invoiceService.getInvoiceById(id);
            currentInvoiceDTO.setInvoiceProduct(invoiceProductService.getAllInvoiceProductsByInvoiceId(id));
        }

        model.addAttribute("invoice", currentInvoiceDTO);
        model.addAttribute("invoiceProducts", currentInvoiceDTO.getInvoiceProduct());

        return "invoice/purchase-invoice-update";
    }

    @PostMapping("/update/add-invoice-product")
    public String addInvoiceProductInUpdatePage(InvoiceProductDTO invoiceProductDTO) {

        invoiceProductDTO.setName(invoiceProductDTO.getProductDTO().getName());
        currentInvoiceDTO.getInvoiceProduct().add(invoiceProductDTO);
        this.active = false;
        return "redirect:/purchase-invoice/update";
    }

    @PostMapping("/update/delete-invoice-product")
    public String deleteInvoiceProductInUpdatePage(InvoiceProductDTO invoiceProductDTO){

        currentInvoiceDTO.getInvoiceProduct().removeIf(obj -> obj.equals(invoiceProductDTO));
        this.active = currentInvoiceDTO.getInvoiceProduct().size() == 0;

        return "redirect:/purchase-invoice/update";
    }

    @PostMapping("/update/{id}")
    public String updateInvoice(@PathVariable("id") Long id, InvoiceDTO invoiceDTO){

        InvoiceDTO updatedInvoice = invoiceService.update(invoiceDTO, id);
        currentInvoiceDTO.getInvoiceProduct().forEach(obj -> obj.setInvoiceDTO(updatedInvoice));
        invoiceProductService.updateInvoiceProducts(id,currentInvoiceDTO.getInvoiceProduct());
        this.active = true;
        return "redirect:/purchase-invoice/list";
    }

    // Delete ----------------------------------------------------------------------------------------------------------
    @GetMapping("/delete/{id}")
    public String deleteInvoiceById(@PathVariable("id") Long id){

        invoiceProductService.deleteInvoiceProducts(id);
        invoiceService.deleteInvoiceById(id);
        return "redirect:/purchase-invoice/list";

    }

    @ModelAttribute
    public void addAttributes(Model model) {

        model.addAttribute("date", new Date());
        model.addAttribute("localDateTime", LocalDateTime.now());
        model.addAttribute("localDate", LocalDate.now());
        model.addAttribute("java8Instant", Instant.now());

        model.addAttribute("product", new InvoiceProductDTO());
        model.addAttribute("active", active);
        model.addAttribute("products", productService.getAllProductsByCompany());
        model.addAttribute("clients", clientVendorService.getAllClientVendorsByType(CompanyType.CLIENT));

    }

}
