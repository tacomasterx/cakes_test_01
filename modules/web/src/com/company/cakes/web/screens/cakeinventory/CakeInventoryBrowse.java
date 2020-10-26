package com.company.cakes.web.screens.cakeinventory;

import com.haulmont.cuba.gui.screen.*;
import com.company.cakes.entity.CakeInventory;

@UiController("cakes_CakeInventory.browse")
@UiDescriptor("cake-inventory-browse.xml")
@LookupComponent("cakeInventoriesTable")
@LoadDataBeforeShow
public class CakeInventoryBrowse extends StandardLookup<CakeInventory> {
}