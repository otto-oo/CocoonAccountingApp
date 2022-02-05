package com.cocoon.entity;

import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(BaseEntityListener.class)
@Setter
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "createdBy")
    private String createdBy;
    @Column(name = "createdTime")
    private LocalDateTime createdTime;
    @Column(name = "updatedBy")
    private String updatedBy;
    @Column(name = "updatedTime")
    private LocalDateTime updatedTime;
}
