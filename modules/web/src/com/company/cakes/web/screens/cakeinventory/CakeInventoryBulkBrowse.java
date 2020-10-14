package com.company.cakes.web.screens.cakeinventory;

import com.haulmont.cuba.gui.screen.*;
import com.company.cakes.entity.CakeInventory;

@UiController("cakes_CakeInventory.bulkBrowse")
@UiDescriptor("cake-inventory-bulk-browse.xml")
@LookupComponent("cakeInventoriesTable")
@LoadDataBeforeShow
public class CakeInventoryBulkBrowse extends StandardLookup<CakeInventory> {
}