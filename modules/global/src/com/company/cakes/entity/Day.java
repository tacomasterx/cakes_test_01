package com.company.cakes.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum Day implements EnumClass<String> {

    SUN("A"),
    MON("B"),
    TUE("C"),
    WED("D"),
    THU("E"),
    FRI("F"),
    SAT("G");

    private String id;

    Day(String value) {
        this.id = value;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static Day fromId(String id) {
        for (Day at : Day.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}