package com.cocoon.implementation;

import com.cocoon.entity.InvoiceProduct;
import com.cocoon.entity.Stock;
import com.cocoon.enums.InvoiceType;
import com.cocoon.repository.ProductRepository;
import com.cocoon.repository.StockRepository;
import com.cocoon.service.StockService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;
    private final ProductRepository productRepository;

    public StockServiceImpl(StockRepository stockRepository, ProductRepository productRepository) {
        this.stockRepository = stockRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void saveToStock(InvoiceProduct invoiceProduct) {
        Stock productStock = Stock.builder()
                .product(invoiceProduct.getProduct())
                .quantity(invoiceProduct.getQty())
                .price(invoiceProduct.getPrice())
                .remainingQuantity(this.calculateQty(invoiceProduct))
                .invoiceDate(invoiceProduct.getInvoice().getInvoiceDate())
                .build();
        stockRepository.save(productStock);
    }

    private int calculateQty(InvoiceProduct invoiceProduct) {
        int formerQuantity = productRepository.findById(invoiceProduct.getProduct().getId()).get().getQty();
        if (invoiceProduct.getInvoice().getInvoiceType().equals(InvoiceType.SALE)){
            return formerQuantity - invoiceProduct.getQty();
        }
        return formerQuantity + invoiceProduct.getQty();
    }



}
