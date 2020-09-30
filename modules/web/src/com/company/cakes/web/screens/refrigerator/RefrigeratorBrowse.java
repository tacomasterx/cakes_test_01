package com.company.cakes.web.screens.refrigerator;

import com.haulmont.cuba.gui.screen.*;
import com.company.cakes.entity.Refrigerator;

@UiController("cakes_Refrigerator.browse")
@UiDescriptor("refrigerator-browse.xml")
@LookupComponent("refrigeratorsTable")
@LoadDataBeforeShow
public class RefrigeratorBrowse extends StandardLookup<Refrigerator> {
}