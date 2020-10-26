package com.company.cakes.entity;

import com.haulmont.chile.core.annotations.MetaClass;
import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.cuba.core.entity.BaseUuidEntity;

@MetaClass(name = "cakes_Tip")
public class Tip extends BaseUuidEntity {
    private static final long serialVersionUID = -1518697445801987886L;

    @MetaProperty
    private Integer row;

    @MetaProperty
    private Double totalBill;

    @MetaProperty
    private String sex;

    @MetaProperty
    private String smoker;

    @MetaProperty
    private String day;

    @MetaProperty
    private String time;

    @MetaProperty
    private Integer size;

    @MetaProperty
    private Double tip;

    public Double getTip() {
        return tip;
    }

    public void setTip(Double tip) {
        this.tip = tip;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Time getTime() {
        return time == null ? null : Time.fromId(time);
    }

    public void setTime(Time time) {
        this.time = time == null ? null : time.getId();
    }

    public Day getDay() {
        return day == null ? null : Day.fromId(day);
    }

    public void setDay(Day day) {
        this.day = day == null ? null : day.getId();
    }

    public Smoker getSmoker() {
        return smoker == null ? null : Smoker.fromId(smoker);
    }

    public void setSmoker(Smoker smoker) {
        this.smoker = smoker == null ? null : smoker.getId();
    }

    public Sex getSex() {
        return sex == null ? null : Sex.fromId(sex);
    }

    public void setSex(Sex sex) {
        this.sex = sex == null ? null : sex.getId();
    }

    public Double getTotalBill() {
        return totalBill;
    }

    public void setTotalBill(Double totalBill) {
        this.totalBill = totalBill;
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }
}