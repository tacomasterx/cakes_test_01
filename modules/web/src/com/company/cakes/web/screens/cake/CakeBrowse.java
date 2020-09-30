package com.company.cakes.web.screens.cake;

import com.haulmont.cuba.gui.screen.*;
import com.company.cakes.entity.Cake;

@UiController("cakes_Cake.browse")
@UiDescriptor("cake-browse.xml")
@LookupComponent("cakesTable")
@LoadDataBeforeShow
public class CakeBrowse extends StandardLookup<Cake> {
}