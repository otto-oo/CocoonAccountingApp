package com.cocoon.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "payee")
@JsonIgnoreProperties(value={"hibernate_Lazy_Initializer"}, ignoreUnknown = true)
public class Payee extends BaseEntity{

    private String name;

    private List<Object> accountIdentifications = new ArrayList< Object >();

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Address AddressObject;
}
