package com.cocoon.implementation;

import com.cocoon.dto.PaymentDTO;
import com.cocoon.service.PaymentService;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Override
    public PaymentDTO getPaymentById(Long id) {
        return null;
    }
}
