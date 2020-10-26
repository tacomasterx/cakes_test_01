package com.company.cakes.web.screens.exported;

import com.haulmont.cuba.gui.screen.*;
import com.company.cakes.entity.Exported;

@UiController("cakes_Exported.edit")
@UiDescriptor("exported-edit.xml")
@EditedEntityContainer("exportedDc")
@LoadDataBeforeShow
public class ExportedEdit extends StandardEditor<Exported> {
}