package com.company.cakes.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.chile.core.annotations.NumberFormat;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.CurrencyLabelPosition;
import com.haulmont.cuba.core.entity.annotation.CurrencyValue;
import com.haulmont.cuba.core.entity.annotation.Listeners;
import com.haulmont.cuba.core.entity.annotation.PublishEntityChangedEvents;

import javax.persistence.*;
import java.math.BigDecimal;

@PublishEntityChangedEvents
@Table(name = "CAKES_CAKE", indexes = {
        @Index(name = "IDX_CAKES_CAKE_NAME", columnList = "NAME")
})
@Entity(name = "cakes_Cake")
@NamePattern("%s|name")
@Listeners("cakes_CakeChangedListener")
public class Cake extends StandardEntity {
    private static final long serialVersionUID = -7199124741907963340L;


    @Column(name = "NAME")
    private String name;

    @NumberFormat(pattern = "0.00", decimalSeparator = ".", groupingSeparator = ",")
    @Column(name = "PRICE")
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRICE_GROUP_ID")
    private PriceGroup priceGroup;

    @CurrencyValue(currency = "g", labelPosition = CurrencyLabelPosition.RIGHT)
    @Column(name = "WEIGHT")
    private Integer weight;

    @Column(name = "SKU")
    @NumberFormat(pattern = "00000000000")
    private Long sku;

    public Long getSku() {
        return sku;
    }

    public void setSku(Long sku) {
        this.sku = sku;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public PriceGroup getPriceGroup() {
        return priceGroup;
    }

    public void setPriceGroup(PriceGroup priceGroup) {
        this.priceGroup = priceGroup;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}