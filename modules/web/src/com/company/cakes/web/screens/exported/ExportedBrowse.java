package com.company.cakes.web.screens.exported;

import com.haulmont.cuba.gui.screen.*;
import com.company.cakes.entity.Exported;

@UiController("cakes_Exported.browse")
@UiDescriptor("exported-browse.xml")
@LookupComponent("exportedsTable")
@LoadDataBeforeShow
public class ExportedBrowse extends StandardLookup<Exported> {
}