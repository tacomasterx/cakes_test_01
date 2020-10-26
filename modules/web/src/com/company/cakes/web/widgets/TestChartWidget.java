package com.company.cakes.web.widgets;

import com.google.common.collect.ImmutableMap;
import com.haulmont.addon.dashboard.web.annotation.DashboardWidget;
import com.haulmont.addon.dashboard.web.events.DashboardEvent;
import com.haulmont.addon.dashboard.web.widget.RefreshableWidget;
import com.haulmont.charts.gui.components.charts.PieChart;
import com.haulmont.charts.gui.data.ListDataProvider;
import com.haulmont.charts.gui.data.MapDataItem;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.gui.screen.ScreenFragment;
import com.haulmont.cuba.gui.screen.Subscribe;
import com.haulmont.cuba.gui.screen.UiController;
import com.haulmont.cuba.gui.screen.UiDescriptor;

import javax.inject.Inject;
import java.util.Iterator;
import java.util.List;

@UiController("cakes_TestChartWidget")
@UiDescriptor("test-chart-widget.xml")
@DashboardWidget(name = "Price Group Chart")
public class TestChartWidget extends ScreenFragment implements RefreshableWidget {

    @Inject
    private PieChart cakePriceChart;
    @Inject
    private DataManager dataManager;

    @Subscribe
    public void onAfterInit(AfterInitEvent event) {
        cakePriceChart.setDataProvider(getPriceGroupList());
    }



    @Override
    public void refresh(DashboardEvent dashboardEvent) {
        cakePriceChart.setDataProvider(getPriceGroupList());
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