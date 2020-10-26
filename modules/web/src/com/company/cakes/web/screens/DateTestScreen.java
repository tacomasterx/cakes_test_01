package com.company.cakes.web.screens;

import com.company.cakes.entity.CakeInventory;
import com.company.cakes.entity.Refrigerator;
import com.company.cakes.web.screens.cakeinventory.CakeInventoryBrowseByRefri;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.View;
import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.UiComponents;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.components.data.table.ContainerTableItems;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.model.DataComponents;
import com.haulmont.cuba.gui.model.DataContext;
import com.haulmont.cuba.gui.screen.*;
import com.haulmont.cuba.gui.xml.layout.ComponentsFactory;
import org.slf4j.Logger;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.List;


@UiController("cakes_DateTestScreen")
@UiDescriptor("date-test-screen.xml")
public class DateTestScreen extends Screen {

    @Inject
    private DataManager dataManager;
    @Inject
    private ComponentsFactory componentsFactory;
    @Inject
    private DataComponents dataComponents;
    @Inject
    private DataContext dataContext;
    @Inject
    private Logger log;
    @Inject
    private TabSheet tabSheet;
    @Inject
    private UiComponents uiComponents;
    @Inject
    private ScreenBuilders screenBuilders;

    List<Refrigerator> refrigerators;

    private CollectionContainer<CakeInventory> cakeInventoriesManualDc;
    private CollectionLoader<CakeInventory> cakeInventoriesManualDl;
    

    @Subscribe("btnShowDate")
    public void onBtnShowDateClick(Button.ClickEvent event) {
       
        LocalDate localDate = LocalDate.now();
        String smolYear = "" + localDate.getYear() ;
        //log.info("Variable localDate = {}", "" + localDate.getDayOfMonth() + localDate.getMonthValue() + smolDate.substring(2) );
        String smolDate = "" + dateZerofill(localDate.getDayOfMonth()) + dateZerofill(localDate.getMonthValue()) + smolYear.substring(2);
        log.info("Variable localDate = {}", smolDate);
    }

    @Subscribe
    public void onInit(InitEvent event) {
        refrigerators = dataManager.loadValue("select e from cakes_Refrigerator e", Refrigerator.class).list();
        createManualLoader();
        log.info("Variable view = {}", cakeInventoriesManualDc.getItems().size());

        for (int i = 0; i < refrigerators.size(); i++) {

            Label label = componentsFactory.createComponent(Label.class);
            label.setValue("" + refrigerators.get(i).getName());

            Table<CakeInventory> table = uiComponents.create(Table.of(CakeInventory.class));
            table.setItems(new ContainerTableItems<>(cakeInventoriesManualDc));

            VBoxLayout tabContent = componentsFactory.createComponent(VBoxLayout.class);
            tabContent.setSpacing(true);
            tabContent.setMargin(true, false, true, false);

            tabContent.add(label);
            tabContent.add(table);
            tabContent.expand(table);

            Button showScreenButton = uiComponents.create(Button.class);
            showScreenButton.setCaption("Mostrar tabla");
            int finalI = i;
            showScreenButton.addClickListener(e -> showRefrigeratorInventory(finalI) );
            tabContent.add(showScreenButton);


            TabSheet.Tab tab = tabSheet.addTab("tab" + i, tabContent);
            tab.setCaption("" + refrigerators.get(i).getName());

        }
    }

    private void createManualLoader() {

        cakeInventoriesManualDc = dataComponents.createCollectionContainer(CakeInventory.class);

        cakeInventoriesManualDl = dataComponents.createCollectionLoader();
        cakeInventoriesManualDl.setContainer(cakeInventoriesManualDc);
        cakeInventoriesManualDl.setDataContext(dataContext);
        cakeInventoriesManualDl.setQuery("select e from cakes_CakeInventory e");
        cakeInventoriesManualDl.setView(View.MINIMAL);

        log.info("Variable view = {}", cakeInventoriesManualDc.getItems());
    }

    private String dateZerofill(Integer number){
        if (number > 9) {
            return "" + number;
        }
        return "0" + number;
    }

    @Subscribe("btnShowRefri")
    public void onBtnShowRefriClick(Button.ClickEvent event) {

        showRefrigeratorInventory(1);
    }

    private void showRefrigeratorInventory(int index){
        screenBuilders.screen(this)
                .withScreenClass(CakeInventoryBrowseByRefri.class)
//                .withAfterCloseListener(e -> {
//                    notifications.create().withCaption("Closed").show();
//                })
                .withOptions(new InventoryByRefrigeratorOptions(refrigerators.get(index)))
                .withLaunchMode(OpenMode.DIALOG)
                .build()
                .show();
    }
}