package com.cocoon.service;


import com.cocoon.entity.InvoiceProduct;

public interface StockService {

    void saveToStock(InvoiceProduct invoiceProduct);

}
