package com.cocoon.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name="payment")
public class Payment extends BaseEntity{

    private String clientName;
    private int amount;
    private boolean isPaid;

}
