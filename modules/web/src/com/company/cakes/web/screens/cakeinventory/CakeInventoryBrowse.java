package com.company.cakes.web.screens.cakeinventory;

import com.google.common.collect.ImmutableMap;
import com.haulmont.charts.gui.components.charts.PieChart; // IMPORTANTE: Agregar plugin Charts desde marketplace.
import com.haulmont.charts.gui.data.ListDataProvider;
import com.haulmont.charts.gui.data.MapDataItem;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.gui.screen.*;
import com.company.cakes.entity.CakeInventory;
//import org.slf4j.Logger;

import javax.inject.Inject;
import java.util.Iterator;
import java.util.List;

@UiController("cakes_CakeInventory.browse")
@UiDescriptor("cake-inventory-browse.xml")
@LookupComponent("cakeInventoriesTable")
@LoadDataBeforeShow
public class CakeInventoryBrowse extends StandardLookup<CakeInventory> {

    @Inject
    private PieChart cakePriceChart; //Declaración de la gráfica a modificar
    @Inject
    private DataManager dataManager; //Herramienta para devolver data a partir de consultas.
                                    // dataManager.loadvalue("consulta", TipoDeDato.class).parameters("parametro", valor).(one|list|etc)()

    //  @Inject
    //   private Logger log; Herramienta para mostrar mensajes en consola

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) { //Evento que ocurre antes de que la imagen sea desplegada en pantalla.

        cakePriceChart.setDataProvider(getPriceGroupList());  //Asignar DataLoader a la gráfica de forma manual

    }


    public ListDataProvider getPriceGroupList() {  //Función para obtener la lista de precios como un objeto de la clase ListDataProvider

        ListDataProvider dataProvider = new ListDataProvider();
        List<String> priceGroupList = dataManager.loadValue("select e.name from cakes_PriceGorup e", String.class).list(); //Obtener todos los grupos de precio

        Iterator iterator = priceGroupList.iterator(); // Iterar los grupos de precio
        while(iterator.hasNext()) {
            String string = iterator.next().toString();
            dataProvider.addItem(new MapDataItem(ImmutableMap.of("priceGroup", string, "quantity", getPriceGroupCount(string))));
            //Llenar la ListDataProvider a retornar con los campos requeridos por la grafica.   Parámetros ( "nombre del title-field", título, "n de value-field", valor)
        }
        //log.info("Variable foo = {}", dataProvider.getItem(2));
        return dataProvider; // Retorno de la lista armada previamente
    }

    public Integer getPriceGroupCount(String priceGroup) { //Funcion que saca el conteo de todos los pasteles que comparten grupo de precio.
        Integer result;
        result =  dataManager.loadValue( "select  count(e) from cakes_CakeInventory e, cakes_Cake f where e.cake.id = f.id and f.priceGroup.name = :priceGroup", Integer.class)
                .parameter("priceGroup", priceGroup).one();
        //log.info("Variable foo = {}", result);
        return result;
    }


}