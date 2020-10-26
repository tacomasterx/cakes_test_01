package com.company.cakes.entity;

import com.haulmont.chile.core.annotations.MetaClass;
import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.cuba.core.entity.BaseIntegerIdEntity;
import com.haulmont.cuba.core.entity.HasUuid;

import java.util.UUID;

@MetaClass(name = "cakes_CakePivot")
public class CakePivot extends BaseIntegerIdEntity implements HasUuid {
    private static final long serialVersionUID = -2663993588083926091L;

    @MetaProperty
    private String priceGroup;

    @MetaProperty
    private Integer count;

    @MetaProperty
    private String store;

    @MetaProperty
    private String refrigerator;

    private UUID uuid;

    public String getRefrigerator() {
        return refrigerator;
    }

    public void setRefrigerator(String refrigerator) {
        this.refrigerator = refrigerator;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getPriceGroup() {
        return priceGroup;
    }

    public void setPriceGroup(String priceGroup) {
        this.priceGroup = priceGroup;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
}