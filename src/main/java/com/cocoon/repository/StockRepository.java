package com.cocoon.repository;

import com.cocoon.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.chrono.IsoChronology;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {


    Stock findFirstByProduct_IdAndRemainingQuantityNot(Long product_id, int qty);
}
