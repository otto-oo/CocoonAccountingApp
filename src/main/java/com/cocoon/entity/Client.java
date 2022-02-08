package com.cocoon.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "sptable")
@NoArgsConstructor
@Getter
@Setter
public class Client extends BaseEntity {

    private String companyName;
}
