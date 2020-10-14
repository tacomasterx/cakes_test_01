package com.company.cakes.web.screens.cakeinventory;

import com.company.cakes.entity.CakeInventory;
import com.company.cakes.entity.Refrigerator;
import com.company.cakes.entity.Store;
import com.company.cakes.web.screens.RefrigeratorBrowseOptions;
import com.company.cakes.web.screens.refrigerator.RefrigeratorByStoreBrowse;
import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.components.Action;
import com.haulmont.cuba.gui.components.HasValue;
import com.haulmont.cuba.gui.components.PickerField;
import com.haulmont.cuba.gui.screen.*;

import javax.inject.Inject;

//import org.slf4j.Logger;

@UiController("cakes_CakeInventory.edit")
@UiDescriptor("cake-inventory-edit.xml")
@EditedEntityContainer("cakeInventoryDc")
@LoadDataBeforeShow
public class CakeInventoryEdit extends StandardEditor<CakeInventory> {

    @Inject
    private PickerField<Refrigerator> refrigeratorField; //Cargando el PickerField de refrigerador

    @Inject
    private PickerField<Store> storeField; //Cargando el PickerField de tienda

    @Inject
    private ScreenBuilders screenBuilders; // Herramienta para desplegar pantallas

    //    @Inject
    //    private Logger log;

    @Subscribe("storeField")
    public void onStoreFieldValueChange(HasValue.ValueChangeEvent<Store> event) { //Función para deshabilitar el campo de refri sino hay tienda seleccionada.
        //log.info("Variable foo = {}", "Field value change");
        refrigeratorField.setEnabled(true);
    }

// log.info("Variable foo = {}", "Field value change");

   @Subscribe("refrigeratorField.lookup")
    protected void refrigeratorFieldLookupActionPerformed(Action.ActionPerformedEvent event) { //Evento de darle click al lookup del Picker
       Store store = storeField.getValue(); //Tomar valor de un elemento (picker de refris)
       assert store != null;  //Asegurarse de evitar casos nulos
       screenBuilders.lookup(Refrigerator.class, this)
                .withField(refrigeratorField) // Picker a donde retornará la selección
                .withScreenClass(RefrigeratorByStoreBrowse.class) // specific lookup screen
                .withLaunchMode(OpenMode.DIALOG)    // open as modal dialog
                .withOptions(new RefrigeratorBrowseOptions(store))// ScreenOptions para el evento de la otra pantalla (ver comentarios de esa pantalla)
                .build()
                .show();
    }

} //dataManager.loadValue("select e.name from cakes_PriceGorup e", String.class).list();