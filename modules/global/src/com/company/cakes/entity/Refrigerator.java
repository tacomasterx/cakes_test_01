package com.company.cakes.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.*;

@Table(name = "CAKES_REFRIGERATOR")
@Entity(name = "cakes_Refrigerator")
@NamePattern("%s|name")
public class Refrigerator extends StandardEntity {
    private static final long serialVersionUID = -6036154171267112251L;

    @Column(name = "NAME")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STORE_ID")
    private Store store;

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}