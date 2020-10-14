package com.company.cakes.web.screens.cake;

import com.company.cakes.entity.PriceGroup;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.gui.Dialogs;
import com.haulmont.cuba.gui.components.DialogAction;
import com.haulmont.cuba.gui.components.HasValue;
import com.haulmont.cuba.gui.components.PickerField;
import com.haulmont.cuba.gui.components.TextField;
import com.haulmont.cuba.gui.screen.*;
import com.company.cakes.entity.Cake;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.Objects;

@UiController("cakes_Cake.edit")
@UiDescriptor("cake-edit.xml")
@EditedEntityContainer("cakeDc")
@LoadDataBeforeShow
public class CakeEdit extends StandardEditor<Cake> {

    @Inject
    private TextField<BigDecimal> priceField;
    @Inject
    private PickerField<PriceGroup> priceGroupField;
    @Inject
    private Dialogs dialogs;
    @Inject
    private DataManager dataManager;

    @Subscribe("priceGroupField")
    public void onPriceGroupFieldFieldValueChange(PickerField.FieldValueChangeEvent<PriceGroup> event) {
       // priceField.setEnabled(false);
       // priceField.setVisible(true);
    }

    @Subscribe("priceGroupField")
    public void onPriceGroupFieldValueChange(HasValue.ValueChangeEvent<PriceGroup> event) {
        //priceField.setEnabled(false);
        PriceGroup priceGroup = event.getValue();
        assert priceGroup != null;
        if(priceGroup.getName().equals("Gratis")){
            priceField.setValue(new BigDecimal("0.0"));
            priceField.setVisible(false);
        }
         else
             priceField.setVisible(true);
    }

    @Subscribe
    public void onBeforeCommitChanges(BeforeCommitChangesEvent event) {
        if (getEditedEntity().getPrice() == null || getEditedEntity().getPrice().equals(new BigDecimal("0.00"))) {
            PriceGroup priceGroup = dataManager.loadValue("select e from cakes_PriceGorup e where e.name = 'Gratis'",
                    PriceGroup.class).one();
            dialogs.createOptionDialog()
                    .withCaption("Question")
                    .withMessage("Are you sure this is a free product?")
                    .withActions(
                            new DialogAction(DialogAction.Type.YES).withHandler(e -> {
                                getEditedEntity().setPrice(new BigDecimal("0.0"));
                                getEditedEntity().setPriceGroup(priceGroup);
                                // retry commit and resume action
                                event.resume(commitChanges());
                            }),
                            new DialogAction(DialogAction.Type.NO).withHandler(e -> {
                                // trigger standard commit and resume action
                                //event.resume();
                                event.preventCommit();
                            })
                    )
                    .show();

            event.preventCommit();
        }
    }



}