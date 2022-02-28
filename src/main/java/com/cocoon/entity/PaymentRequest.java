package com.cocoon.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "payment_request")
@JsonIgnoreProperties(value={"hibernate_Lazy_Initializer"}, ignoreUnknown = true)
public class PaymentRequest extends BaseEntity{

    private String type;
    private String reference;
    private String paymentIdempotencyId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Amount AmountObject;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Payee PayeeObject;

}
