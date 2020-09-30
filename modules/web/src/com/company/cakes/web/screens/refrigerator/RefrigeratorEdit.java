package com.company.cakes.web.screens.refrigerator;

import com.haulmont.cuba.gui.screen.*;
import com.company.cakes.entity.Refrigerator;

@UiController("cakes_Refrigerator.edit")
@UiDescriptor("refrigerator-edit.xml")
@EditedEntityContainer("refrigeratorDc")
@LoadDataBeforeShow
public class RefrigeratorEdit extends StandardEditor<Refrigerator> {
}