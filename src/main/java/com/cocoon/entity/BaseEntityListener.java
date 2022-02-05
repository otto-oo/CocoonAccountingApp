package com.cocoon.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.stereotype.Component;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@Component
public class BaseEntityListener extends AuditingEntityListener {

    @PrePersist
    public void onPrePersist(BaseEntity baseEntity) {
        baseEntity.setCreatedBy("1");//todo this will be changed with the creator user id
        baseEntity.setCreatedTime(LocalDateTime.now());
        baseEntity.setUpdatedBy("1");//todo this will be changed with the creator user id
        baseEntity.setUpdatedTime(LocalDateTime.now());
    }

    @PreUpdate
    public void pnPreUpdate(BaseEntity baseEntity) {
        baseEntity.setUpdatedBy("1");//todo this will be changed with the creator user id
        baseEntity.setUpdatedTime(LocalDateTime.now());
    }
}
