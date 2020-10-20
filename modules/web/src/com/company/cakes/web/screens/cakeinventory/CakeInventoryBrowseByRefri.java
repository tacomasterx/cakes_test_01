package com.company.cakes.web.screens.cakeinventory;

import com.company.cakes.entity.CakeInventory;
import com.company.cakes.entity.Refrigerator;
import com.company.cakes.web.screens.InventoryByRefrigeratorOptions;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.gui.BulkEditors;
import com.haulmont.cuba.gui.actions.list.BulkEditAction;
import com.haulmont.cuba.gui.app.core.bulk.ColumnsMode;
import com.haulmont.cuba.gui.components.Action;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.GroupTable;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.screen.*;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Arrays;
import java.util.Collections;

@UiController("cakes_CakeInventory.browseByRefri")
@UiDescriptor("cake-inventory-browse-by-refri.xml")
@LookupComponent("cakeInventoriesTable")
@LoadDataBeforeShow
public class CakeInventoryBrowseByRefri extends StandardLookup<CakeInventory> implements ScreenOptions {

    @Inject
    private CollectionLoader<CakeInventory> cakeInventoriesDl;
    @Inject
    private GroupTable<CakeInventory> cakeInventoriesTable;
    @Named("cakeInventoriesTable.bulkEdit")
    private BulkEditAction cakeInventoriesTableBulkEdit;
    @Inject
    private Metadata metadata;
    @Inject
    private BulkEditors bulkEditors;
    private Refrigerator refrigerator;


    @Subscribe
    public void onInit(InitEvent event) {

        ScreenOptions options = event.getOptions();           // Recibir screen options como parámetro
        if (options instanceof InventoryByRefrigeratorOptions) { // Verificar que las options sean de la clase requerida
            Refrigerator refrigerator = ((InventoryByRefrigeratorOptions) options).getRefrigerator(); //Tomar la option de clase Refrigerator
            //log.info("Variable foo = {}", store.getName());
            this.refrigerator = refrigerator; //Asignar ese valor a una variable de screen
        }
    }

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        if (refrigerator == null)
            throw new IllegalStateException("Refrigerator parameter is null"); //Mensaje de error en caso de que no haya options.
        cakeInventoriesDl.setParameter("refrigerator", refrigerator); // Se le da valor al parámetro del DataLoader
        cakeInventoriesDl.load();
    }

    @Subscribe("selectAll")
    public void onSelectAllClick(Button.ClickEvent event) {
        cakeInventoriesTable.selectAll();
    }

    @Subscribe("remate")
    public void onRemateClick(Button.ClickEvent event) {
        cakeInventoriesTable.selectAll();
        cakeInventoriesTableBulkEdit.execute();
        cakeInventoriesTable.setSelected(Collections.emptyList());
        this.closeWithDefaultAction()  ;
    }

    @Subscribe("cakeInventoriesTable.bulkEdit")
    public void onCakeInventoriesTableBulkEdit(Action.ActionPerformedEvent event) {
        bulkEditors.builder(metadata.getClassNN(CakeInventory.class), cakeInventoriesTable.getSelected(), this)
                .withListComponent(cakeInventoriesTable)
                .withColumnsMode(ColumnsMode.ONE_COLUMN)
                .withIncludeProperties(Arrays.asList("status"))
                .create()
                .show();
    }

    

}