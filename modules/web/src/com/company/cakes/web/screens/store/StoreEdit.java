package com.company.cakes.web.screens.store;

import com.haulmont.cuba.gui.screen.*;
import com.company.cakes.entity.Store;

@UiController("cakes_Store.edit")
@UiDescriptor("store-edit.xml")
@EditedEntityContainer("storeDc")
@LoadDataBeforeShow
public class StoreEdit extends StandardEditor<Store> {
}