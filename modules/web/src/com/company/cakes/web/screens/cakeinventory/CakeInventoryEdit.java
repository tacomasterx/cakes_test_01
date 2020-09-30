package com.company.cakes.web.screens.cakeinventory;

import com.company.cakes.entity.CakeInventory;
import com.company.cakes.entity.Refrigerator;
import com.company.cakes.entity.Store;
import com.company.cakes.web.screens.refrigerator.RefrigeratorByStoreBrowse;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.components.Action;
import com.haulmont.cuba.gui.components.HasValue;
import com.haulmont.cuba.gui.components.PickerField;
import com.haulmont.cuba.gui.screen.*;
import org.slf4j.Logger;

import javax.inject.Inject;

@UiController("cakes_CakeInventory.edit")
@UiDescriptor("cake-inventory-edit.xml")
@EditedEntityContainer("cakeInventoryDc")
@LoadDataBeforeShow
public class CakeInventoryEdit extends StandardEditor<CakeInventory> {

    @Inject
    private PickerField<Refrigerator> refrigeratorField;

    @Inject
    private PickerField<Store> storeField;

    @Inject
    private ScreenBuilders screenBuilders;

    @Inject
    private Logger log;
    @Inject
    private DataManager dataManager;

    @Subscribe("storeField")
    public void onStoreFieldValueChange(HasValue.ValueChangeEvent<Store> event) {
        log.info("Variable foo = {}", "Field value change");
        refrigeratorField.setEnabled(true);
    }



// log.info("Variable foo = {}", "Field value change");

   @Subscribe("refrigeratorField.lookup")
    protected void refrigeratorFieldLookupActionPerformed(Action.ActionPerformedEvent event) {
        Store store = storeField.getValue();
        screenBuilders.lookup(Refrigerator.class, this)
                .withField(refrigeratorField)
                .withScreenClass(RefrigeratorByStoreBrowse.class) // specific lookup screen
                .withLaunchMode(OpenMode.DIALOG)    // open as modal dialog
                //.withOptions(new RefrigeratorBrowseOptions())
                .build()
                .show();

    }


} //dataManager.loadValue("select e.name from cakes_PriceGorup e", String.class).list();