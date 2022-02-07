package com.cocoon.entity;

import com.cocoon.enums.InvoiceType;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "invoice")
@NoArgsConstructor
@Getter
@Setter
public class Invoice extends BaseEntity{

    private String invoiceNo;
    private String invoiceStatus;
    private int invoiceNumber;

    @Enumerated(EnumType.STRING)
    private InvoiceType invoiceType;

    @Column(columnDefinition = "DATE")
    private LocalDate invoiceDate;

    private int enabled;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;


}
