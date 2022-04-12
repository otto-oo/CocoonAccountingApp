package com.cocoon.implementation;

import com.cocoon.entity.InvoiceProduct;
import com.cocoon.entity.Stock;
import com.cocoon.enums.InvoiceType;
import com.cocoon.repository.ProductRepository;
import com.cocoon.repository.StockRepository;
import com.cocoon.service.StockService;
import org.springframework.stereotype.Service;


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

        if (invoiceProduct.getInvoice().getInvoiceType().equals(InvoiceType.PURCHASE)){
            Stock productStock = Stock.builder()
                    .product(invoiceProduct.getProduct())
                    .quantity(invoiceProduct.getQty())
                    .price(invoiceProduct.getPrice())
                    .remainingQuantity(invoiceProduct.getQty())
                    .profitLoss(0)
                    .invoiceDate(invoiceProduct.getInvoice().getInvoiceDate())
                    .build();
            stockRepository.save(productStock);
        } else {
            int soldProductQty = invoiceProduct.getQty();
            while ( soldProductQty > 0){
                Stock queuedProductStock = stockRepository.findFirstByProduct_IdAndRemainingQuantityNot(invoiceProduct.getProduct().getId(), 0);
                if (soldProductQty < queuedProductStock.getRemainingQuantity()){
                    queuedProductStock.setRemainingQuantity(queuedProductStock.getRemainingQuantity() - soldProductQty);
                    queuedProductStock.setProfitLoss(queuedProductStock.getProfitLoss() + (soldProductQty * (invoiceProduct.getPrice() - queuedProductStock.getPrice())));

                    stockRepository.save(queuedProductStock);
                    break;
                } else {
                    soldProductQty -= queuedProductStock.getRemainingQuantity();
                    queuedProductStock.setProfitLoss(queuedProductStock.getProfitLoss() + (queuedProductStock.getRemainingQuantity() * (invoiceProduct.getPrice() - queuedProductStock.getPrice())));

                    queuedProductStock.setRemainingQuantity(0);
                    stockRepository.save(queuedProductStock);
                }
            }
        }
    }

}
