package com.cocoon.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "stock_details")
@NoArgsConstructor
@Getter
@Setter
public class Stock extends BaseEntity{


}
