package com.company.cakes.web.screens;

import com.company.cakes.entity.Store;
import com.haulmont.cuba.gui.screen.Screen;
import com.haulmont.cuba.gui.screen.ScreenOptions;
import com.haulmont.cuba.gui.screen.UiController;
import com.haulmont.cuba.gui.screen.UiDescriptor;

@UiController("cakes_RefrigeratorBrowseOptions")
@UiDescriptor("refrigerator-browse-options.xml")
public class RefrigeratorBrowseOptions extends Screen implements ScreenOptions {

    private Store store;

    public RefrigeratorBrowseOptions(Store store){
        this.store = store;
    }

    public Store getStore() {
        return store;
    }

}