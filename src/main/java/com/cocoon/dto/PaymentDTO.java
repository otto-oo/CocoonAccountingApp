package com.cocoon.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PaymentDTO {

    private Long id;
    private String clientName;
    private int amount;
    private boolean isPaid;
}
