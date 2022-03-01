package com.cocoon.entity.payment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(value={"hibernate_Lazy_Initializer"}, ignoreUnknown = true)
@ToString
public class Payment{

    private String applicationUserId = "single-payment-tutorial";
    private String institutionId = "modelo-sandbox";
    private String callback = "https://display-parameters.com/";

    private PaymentRequest PaymentRequestObject = new PaymentRequest();

}