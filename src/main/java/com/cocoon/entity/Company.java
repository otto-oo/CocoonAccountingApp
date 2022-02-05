package com.cocoon.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "company")
public class Company extends BaseEntity {

    private String title;
    private String address1;
    private String address2;
    private String state;
    private String zip;
    private String representative;
    private String email;
    @Column(name = "establishmentDate")
    private String establishmentDate;
    private boolean enabled;

}
