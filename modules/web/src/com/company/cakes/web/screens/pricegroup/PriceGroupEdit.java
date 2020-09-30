package com.company.cakes.web.screens.pricegroup;

import com.haulmont.cuba.gui.screen.*;
import com.company.cakes.entity.PriceGroup;

@UiController("cakes_PriceGorup.edit")
@UiDescriptor("price-group-edit.xml")
@EditedEntityContainer("priceGroupDc")
@LoadDataBeforeShow
public class PriceGroupEdit extends StandardEditor<PriceGroup> {
}