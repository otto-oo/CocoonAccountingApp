package com.cocoon.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "address")
@JsonIgnoreProperties(value={"hibernate_Lazy_Initializer"}, ignoreUnknown = true)
public class Address extends BaseEntity{

    private String country;
}
