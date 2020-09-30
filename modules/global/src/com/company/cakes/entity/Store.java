package com.company.cakes.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "CAKES_STORE")
@Entity(name = "cakes_Store")
@NamePattern("%s|name")
public class Store extends StandardEntity {
    private static final long serialVersionUID = -4601457600389243747L;

    @Column(name = "NAME")
    private String name;

    @Column(name = "CODE")
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}