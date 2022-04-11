package com.cocoon.implementation;

import com.cocoon.entity.Stock;
import com.cocoon.service.StockService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockServiceImpl implements StockService {

    @Override
    public List<Stock> getStocks() {
        return null;
    }
}
