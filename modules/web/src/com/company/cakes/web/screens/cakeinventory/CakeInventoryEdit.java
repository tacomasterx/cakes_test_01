package com.company.cakes.web.screens.cakeinventory;

import com.company.cakes.entity.*;
import com.company.cakes.web.screens.RefrigeratorBrowseOptions;
import com.company.cakes.web.screens.refrigerator.RefrigeratorByStoreBrowse;
import com.haulmont.charts.gui.amcharts.model.Export;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.components.Action;
import com.haulmont.cuba.gui.components.HasValue;
import com.haulmont.cuba.gui.components.PickerField;
import com.haulmont.cuba.gui.screen.*;
import org.apache.commons.math3.analysis.function.Exp;

import javax.inject.Inject;
import java.math.BigDecimal;

@UiController("cakes_CakeInventory.edit")
@UiDescriptor("cake-inventory-edit.xml")
@EditedEntityContainer("cakeInventoryDc")
@LoadDataBeforeShow
public class CakeInventoryEdit extends StandardEditor<CakeInventory> {
    @Inject
    private PickerField<Cake> cakeField;
    @Inject
    private DataManager dataManager;
    @Inject
    private PickerField<Exported> exportedField;
    @Inject
    private PickerField<Refrigerator> refrigeratorField;
    @Inject
    private PickerField<Store> storeField;
    @Inject
    private ScreenBuilders screenBuilders;


    @Subscribe("storeField")
    public void onStoreFieldValueChange(HasValue.ValueChangeEvent<Store> event) { //Función para deshabilitar el campo de refri sino hay tienda seleccionada.
        //log.info("Variable foo = {}", "Field value change");
        refrigeratorField.setEnabled(true);
        refrigeratorField.clear();
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

    @Subscribe("cakeField")
    public void onCakeFieldValueChange(HasValue.ValueChangeEvent<Cake> event) {
        assert event.getValue() != null;
        Cake cake = event.getValue();
        PriceGroup priceGroup = cake.getPriceGroup();
        if (priceGroup.getName().equals("Gratis") || priceGroup.getName().equals("Regalado"))
            exportedField.setEnabled(false);
        else
            exportedField.setEnabled(true);

        exportedField.clear();
    }

    @Subscribe
    public void onBeforeCommitChanges(BeforeCommitChangesEvent event) {
        if (getEditedEntity().getExported() == null ) {
            Exported exported = dataManager.loadValue("select e from cakes_Exported e where e.name = 'No exportado'", Exported.class).one();
            getEditedEntity().setExported(exported);
            event.resume(commitChanges());
        }
    }

}