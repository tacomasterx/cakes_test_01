package com.company.cakes.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "CAKES_PRICE_GORUP")
@Entity(name = "cakes_PriceGorup")
@NamePattern("%s|name")
public class PriceGroup extends StandardEntity {
    private static final long serialVersionUID = 1910423769695343721L;

    @Column(name = "NAME")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}//Second deployment