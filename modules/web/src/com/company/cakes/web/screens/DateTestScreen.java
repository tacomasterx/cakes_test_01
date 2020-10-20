package com.company.cakes.web.screens;

import com.company.cakes.entity.Cake;
import com.company.cakes.entity.CakeInventory;
import com.company.cakes.entity.Refrigerator;
import com.company.cakes.entity.Store;
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
//import org.slf4j.Logger;

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
//    @Inject
//    private Logger log; // Librería para mostrar datos en consola
    @Inject
    private TabSheet tabSheet;

    @Inject
    private UiComponents uiComponents;
    @Inject
    private ScreenBuilders screenBuilders;

    private List<Refrigerator> refrigerators;


    private CollectionContainer<CakeInventory> cakeInventoriesManualDc;
    private CollectionLoader<CakeInventory> cakeInventoriesManualDl;

    private List<CollectionContainer<CakeInventory>> cakeInventoryDcArray;
    private List<CollectionLoader<CakeInventory>> cakeInventoryDlArray;

    @Subscribe("btnShowDate")
    public void onBtnShowDateClick(Button.ClickEvent event) {// Botón usado simplemente para aprender a manejar las fechas
                                                             // Se puede ignorar
        LocalDate localDate = LocalDate.now();
        String smolYear = "" + localDate.getYear() ;
        //log.info("Variable localDate = {}", "" + localDate.getDayOfMonth() + localDate.getMonthValue() + smolDate.substring(2) );
        String smolDate = "" + dateZerofill(localDate.getDayOfMonth()) + dateZerofill(localDate.getMonthValue()) + smolYear.substring(2);
        //log.info("Variable localDate = {}", smolDate);
    }

    @Subscribe
    public void onInit(InitEvent event) { // Acciones que deben tomarse durante la inicialización
        refrigerators = dataManager.loadValue("select e from cakes_Refrigerator e", Refrigerator.class).list();
        //log.info("Variable view = {}", cakeInventoriesManualDc.getItems().size());
        createManualLoader(refrigerators.get(1));
        for (int i = 0; i < refrigerators.size(); i++) {



            Label label = componentsFactory.createComponent(Label.class);
            label.setValue("" + refrigerators.get(i).getName());

            Table<CakeInventory> table = uiComponents.create(Table.of(CakeInventory.class));  //Intento de creación manual de la tabla
            table.setItems(new ContainerTableItems<>(cakeInventoriesManualDc));

               // Declaración de Components Factory requerida para crear tabs.
            VBoxLayout tabContent = componentsFactory.createComponent(VBoxLayout.class);
            tabContent.setSpacing(true);
            tabContent.setMargin(true, false, true, false);

            tabContent.add(label);        //Agregar un elemento de UI a una tab.
            tabContent.add(table);
            tabContent.expand(table);

            Button showScreenButton = uiComponents.create(Button.class);   // Agregar un botón de forma "programática"
            showScreenButton.setCaption("Mostrar tabla");
            int finalI = i;
            showScreenButton.addClickListener(e -> showRefrigeratorInventory(finalI) );
            tabContent.add(showScreenButton);


            TabSheet.Tab tab = tabSheet.addTab("tab" + i, tabContent);   //Crear una nueva tab y meterla a la tabSheet
            tab.setCaption("" + refrigerators.get(i).getName());               //Cambiar el nombre desplegado de la tab.

        }
    }

    private void createManualLoader(Refrigerator refrigerator) { // Intento de crear un Data Loader para la tabla

        cakeInventoriesManualDc = dataComponents.createCollectionContainer(CakeInventory.class);

        View view = new View(CakeInventory.class)
                .addProperty("status")
                .addProperty("cake", new View(Cake.class)
                        .addProperty("name")
                        .addProperty("priceGroup")
                ).addProperty("store", new View(Store.class)
                        .addProperty("name")
                        .addProperty("code")
                ).addProperty("refrigerator")
                .addProperty("exported");

        cakeInventoriesManualDl = dataComponents.createCollectionLoader();
        cakeInventoriesManualDl.setContainer(cakeInventoriesManualDc);
        cakeInventoriesManualDl.setDataContext(dataContext);
        cakeInventoriesManualDl.setQuery("select e from cakes_CakeInventory e where e.refrigerator.id = '" + refrigerator.getId() + "'");
        cakeInventoriesManualDl.setView(view);

       // log.info("Variable view = {}", cakeInventoriesManualDc.getItems());  //
    }

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        refrigerators = dataManager.loadValue("select e from cakes_Refrigerator e", Refrigerator.class).list();
     //   for (int i = 0; i < refrigerators.size(); i++) { }
        cakeInventoriesManualDl.load();  // Esto parece mejorar un poco el asunto de las tablas pero causa un problema de
        //IllegalStateException: Cannot get unfetched attribute [exported] from detached object com.company.cakes.entity.CakeInventory-04379a92-fd37-7ebb-2243-c59fedd99ff2 [detached].
    }



    private String dateZerofill(Integer number){ // Zerofill para las fechas, hay una copia en la parte de
                                                 // Crear SKU con fecha y tipo de precio.
        if (number > 9) {
            return "" + number;
        }
        return "0" + number;
    }

    @Subscribe("btnShowRefri")
    public void onBtnShowRefriClick(Button.ClickEvent event) { // Botón de test para mostrar el inventrario de un Refrigerador

        showRefrigeratorInventory(1);
    }

    private void showRefrigeratorInventory(int index){  // Función para desplegar la screen CakeInventoryBrowseByRefri
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