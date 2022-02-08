package com.cocoon.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "client_vendor_list")
@NoArgsConstructor
@Getter
@Setter
public class ClientVendor extends BaseEntity {

    private String companyName;
}
