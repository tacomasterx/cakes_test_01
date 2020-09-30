package com.company.cakes.web.screens.cakeinventory;

import com.google.common.collect.ImmutableMap;
import com.haulmont.charts.gui.components.charts.PieChart;
import com.haulmont.charts.gui.data.ListDataProvider;
import com.haulmont.charts.gui.data.MapDataItem;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.gui.screen.*;
import com.company.cakes.entity.CakeInventory;
import org.slf4j.Logger;

import javax.inject.Inject;
import java.util.Iterator;
import java.util.List;

@UiController("cakes_CakeInventory.browse")
@UiDescriptor("cake-inventory-browse.xml")
@LookupComponent("cakeInventoriesTable")
@LoadDataBeforeShow
public class CakeInventoryBrowse extends StandardLookup<CakeInventory> {

    @Inject
    private PieChart cakePriceChart;
    @Inject
    private DataManager dataManager;

    @Inject
    private Logger log;

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {

        cakePriceChart.setDataProvider(getPriceGroupList());

    }


    public ListDataProvider getPriceGroupList() {

        ListDataProvider dataProvider = new ListDataProvider();
        List<String> priceGroupList = dataManager.loadValue("select e.name from cakes_PriceGorup e", String.class).list();

        Iterator iterator = priceGroupList.iterator();
        while(iterator.hasNext()) {
            String string = iterator.next().toString();
            dataProvider.addItem(new MapDataItem(ImmutableMap.of("priceGroup", string, "quantity", getPriceGroupCount(string))));
        }
        //log.info("Variable foo = {}", dataProvider.getItem(2));
        return dataProvider;
    }

    public Integer getPriceGroupCount(String priceGroup) {
        Integer result;
        result =  dataManager.loadValue( "select  count(e) from cakes_CakeInventory e, cakes_Cake f where e.cake.id = f.id and f.priceGroup.name = :priceGroup", Integer.class)
                .parameter("priceGroup", priceGroup).one();
        log.info("Variable foo = {}", result);
        return result;
    }


}