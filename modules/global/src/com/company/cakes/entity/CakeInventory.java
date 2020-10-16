package com.company.cakes.entity; //Registro de todos los pasteles individuales, relación uno a muchos con Cake

import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.*;

@Table(name = "CAKES_CAKE_INVENTORY")
@Entity(name = "cakes_CakeInventory")
public class CakeInventory extends StandardEntity {
    private static final long serialVersionUID = -3580928893980221045L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CAKE_ID")
    private Cake cake;

    @Column(name = "STATUS")
    // No usada, pero puede representar el estado de un pastel ejemplo: disponible, caducado, vendido, etc.
    private Integer status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EXPORTED_ID")
    private Exported exported;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STORE_ID")
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REFRIGERATOR_ID")
    private Refrigerator refrigerator;

    public Exported getExported() {
        return exported;
    }

    public void setExported(Exported exported) {
        this.exported = exported;
    }

    public Refrigerator getRefrigerator() {
        return refrigerator;
    }

    public void setRefrigerator(Refrigerator refrigerator) {
        this.refrigerator = refrigerator;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Cake getCake() {
        return cake;
    }

    public void setCake(Cake cake) {
        this.cake = cake;
    }
}