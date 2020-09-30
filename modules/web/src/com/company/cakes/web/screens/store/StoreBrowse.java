package com.company.cakes.web.screens.store;

import com.haulmont.cuba.gui.screen.*;
import com.company.cakes.entity.Store;

@UiController("cakes_Store.browse")
@UiDescriptor("store-browse.xml")
@LookupComponent("storesTable")
@LoadDataBeforeShow
public class StoreBrowse extends StandardLookup<Store> {
}