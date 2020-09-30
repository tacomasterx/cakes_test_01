package com.company.cakes.web.screens.pricegroup;

import com.haulmont.cuba.gui.screen.*;
import com.company.cakes.entity.PriceGroup;

@UiController("cakes_PriceGorup.browse")
@UiDescriptor("price-group-browse.xml")
@LookupComponent("priceGroupsTable")
@LoadDataBeforeShow
public class PriceGroupBrowse extends StandardLookup<PriceGroup> {
}