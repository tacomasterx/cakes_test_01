package com.company.cakes.web.screens.cakeinventory;

import com.company.cakes.entity.CakeInventory;
import com.company.cakes.entity.Refrigerator;
import com.company.cakes.web.screens.InventoryByRefrigeratorOptions;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.GroupTable;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.screen.*;

import javax.inject.Inject;

@UiController("cakes_CakeInventory.browseByRefri")
@UiDescriptor("cake-inventory-browse-by-refri.xml")
@LookupComponent("cakeInventoriesTable")
@LoadDataBeforeShow
public class CakeInventoryBrowseByRefri extends StandardLookup<CakeInventory> implements ScreenOptions {

    @Inject
    private CollectionLoader<CakeInventory> cakeInventoriesDl;
    @Inject
    private GroupTable<CakeInventory> cakeInventoriesTable;
    private Refrigerator refrigerator;


    @Subscribe
    public void onInit(InitEvent event) {
        ScreenOptions options = event.getOptions();
        if (options instanceof InventoryByRefrigeratorOptions) { // Verificar que las options sean de la clase requerida
            Refrigerator refrigerator = ((InventoryByRefrigeratorOptions) options).getRefrigerator();
            //log.info("Variable foo = {}", store.getName());
            this.refrigerator = refrigerator;
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

}