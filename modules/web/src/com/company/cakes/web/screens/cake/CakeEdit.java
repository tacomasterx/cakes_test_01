package com.company.cakes.web.screens.cake;

import com.haulmont.cuba.gui.screen.*;
import com.company.cakes.entity.Cake;

@UiController("cakes_Cake.edit")
@UiDescriptor("cake-edit.xml")
@EditedEntityContainer("cakeDc")
@LoadDataBeforeShow
public class CakeEdit extends StandardEditor<Cake> {
}