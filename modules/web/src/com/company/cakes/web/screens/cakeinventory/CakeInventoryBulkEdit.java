package com.company.cakes.web.screens.cakeinventory;

import com.company.cakes.entity.Refrigerator;
import com.company.cakes.entity.Store;
import com.company.cakes.web.screens.RefrigeratorBrowseOptions;
import com.company.cakes.web.screens.refrigerator.RefrigeratorByStoreBrowse;
import com.haulmont.cuba.gui.Dialogs;
import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.components.Action;
import com.haulmont.cuba.gui.components.DialogAction;
import com.haulmont.cuba.gui.components.HasValue;
import com.haulmont.cuba.gui.components.PickerField;
import com.haulmont.cuba.gui.screen.*;
import com.company.cakes.entity.CakeInventory;

import javax.inject.Inject;

@UiController("cakes_CakeInventory.bulkEdit")
@UiDescriptor("cake-inventory-bulk-edit.xml")
@EditedEntityContainer("cakeInventoryDc")
@LoadDataBeforeShow
public class CakeInventoryBulkEdit extends StandardEditor<CakeInventory> {

    @Inject
    private PickerField<Store> storeField;
    @Inject
    private PickerField<Refrigerator> refrigeratorField;
    @Inject
    private ScreenBuilders screenBuilders;
    @Inject
    private Dialogs dialogs;

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


    @Subscribe
    public void onBeforeCommitChanges(BeforeCommitChangesEvent event) {
        if (getEditedEntity().getStatus() == null) {
            dialogs.createOptionDialog()
                    .withCaption("Question")
                    .withMessage("Do you want to set default status?")
                    .withActions(
                            new DialogAction(DialogAction.Type.YES).withHandler(e -> {
                                getEditedEntity().setStatus(1);

                                // retry commit and resume action
                                event.resume(commitChanges());
                            }),
                            new DialogAction(DialogAction.Type.NO).withHandler(e -> {
                                // trigger standard commit and resume action
                                event.resume();
                            })
                    )
                    .show();

            event.preventCommit();
        }
    }

}