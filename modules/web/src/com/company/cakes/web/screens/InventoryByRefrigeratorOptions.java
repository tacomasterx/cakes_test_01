package com.company.cakes.web.screens;

import com.company.cakes.entity.Refrigerator;
import com.haulmont.cuba.gui.screen.Screen;
import com.haulmont.cuba.gui.screen.ScreenOptions;
import com.haulmont.cuba.gui.screen.UiController;
import com.haulmont.cuba.gui.screen.UiDescriptor;

@UiController("cakes_InventoryByRefrigeratorOptions")
@UiDescriptor("inventory-by-refrigerator-options.xml")
public class InventoryByRefrigeratorOptions extends Screen implements ScreenOptions {

    private Refrigerator refrigerator;

    public InventoryByRefrigeratorOptions(Refrigerator refrigerator){
        this.refrigerator = refrigerator;
    }

    public Refrigerator getRefrigerator(){
        return refrigerator;
    }

}