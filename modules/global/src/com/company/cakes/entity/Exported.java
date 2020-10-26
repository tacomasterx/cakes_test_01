package com.company.cakes.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "CAKES_EXPORTED")
@Entity(name = "cakes_Exported")
@NamePattern("%s|name")
public class Exported extends StandardEntity {
    private static final long serialVersionUID = -5857591987249422190L;

    @Column(name = "NAME")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}