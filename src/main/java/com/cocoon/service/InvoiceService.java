package com.cocoon.service;

import com.cocoon.dto.InvoiceDTO;
import com.cocoon.entity.Invoice;

import java.util.List;

public interface InvoiceService {

    void save(InvoiceDTO invoice);
    List<InvoiceDTO> getAllInvoices();
}
