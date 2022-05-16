package com.cocoon.service;

import com.cocoon.dto.InvoiceProductDTO;
import com.cocoon.enums.InvoiceType;
import com.cocoon.exception.CocoonException;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public interface InvoiceProductService{

    Set<InvoiceProductDTO> save(Set<InvoiceProductDTO> invoiceProductDTOSet);
    Set<InvoiceProductDTO> getAllInvoiceProductsByInvoiceId(Long id);
    void updateInvoiceProducts(Long id, Set<InvoiceProductDTO> invoiceProductDTOs);
    void approveInvoiceProduct(Long id);
    void deleteInvoiceProducts(Long id);
    boolean validateProductQtyForPendingInvoicesIncluded(InvoiceProductDTO dto) throws CocoonException;
    ArrayList<InvoiceProductDTO> getStockReportList();
}
