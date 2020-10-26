package com.company.cakes.web.screens.cake;

import com.company.cakes.entity.CakeInventory;
import com.company.cakes.entity.CakePivot;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.screen.Screen;
import com.haulmont.cuba.gui.screen.Subscribe;
import com.haulmont.cuba.gui.screen.UiController;
import com.haulmont.cuba.gui.screen.UiDescriptor;
import org.slf4j.Logger;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@UiController("cakes_CakePivotTable")
@UiDescriptor("cake-pivot-table.xml")
public class CakePivotTable extends Screen {

    @Inject
    private CollectionContainer<CakePivot> cakesDc;
    @Inject
    private DataManager dataManager;
    @Inject
    private Logger log;


    @Subscribe
    protected void onInit(InitEvent event) {
        List<CakePivot> items = new ArrayList<>();
        int counter = 0;


        List<CakeInventory> cakeInventoryList = dataManager.loadValue("select e from cakes_CakeInventory e ", CakeInventory.class)
                            .list();
        for (CakeInventory cakeInventory : cakeInventoryList) {
            counter++;
            log.info("Variable foo = {}", getPriceGroup(cakeInventory));

            items.add(pivot(counter, getPriceGroup(cakeInventory), 0, getStore(cakeInventory), getRefrigerator(cakeInventory)));

        }



        //items = dataManager.loadValue("select  f.priceGroup.name,  e.store.code, e.refrigerator.name  from cakes_CakeInventory e, cakes_Cake f ", CakePivot.class).list();


//        items.add(pivot(1, "Normal", 1, "Sex.FEMALE", "R34"));
//        items.add(pivot(2, "Normal", 1, "D345", "R34"));
//        items.add(pivot(3, "Normal", 1, "D345", "R45"));
//        items.add(pivot(4, "Normal", 2, "D345", "R34"));
//        items.add(pivot(5, "Barato", 1, "Sex.FEMALE", "Smoker.YES"));
//        items.add(pivot(6, "Muy barato", 1, "Sex.MALE", "Smoker.YES"));
//        items.add(pivot(7, "Regalado", 1, "Sex.FEMALE", "Smoker.YES"));

        cakesDc.setItems(items);
    }

    private CakePivot pivot(int id, String priceGroup, int count, String store, String refrigerator) {
        CakePivot pivot = dataManager.create(CakePivot.class);
        pivot.setId(id);
        pivot.setPriceGroup(priceGroup);
        pivot.setCount(count);
        pivot.setStore(store);
        pivot.setRefrigerator(refrigerator);
        return pivot;
    }

    private String getPriceGroup(CakeInventory cakeInventory){
        UUID cake = dataManager.loadValue("select e.cake.id from cakes_CakeInventory e where e.id = :cakeInventory", UUID.class)
                .parameter("cakeInventory", cakeInventory.getId()).one();
        return dataManager.loadValue("select e.priceGroup.name from cakes_Cake e where e.id = :cakeId", String.class)
                .parameter("cakeId", cake).one();

    }

    private String getStore(CakeInventory cakeInventory){

        return dataManager.loadValue("select e.store.name from cakes_CakeInventory e where e.id = :cakeId", String.class)
                .parameter("cakeId", cakeInventory.getId()).one();

    }

    private String getRefrigerator(CakeInventory cakeInventory){

        return dataManager.loadValue("select e.refrigerator.name from cakes_CakeInventory e where e.id = :cakeId", String.class)
                .parameter("cakeId", cakeInventory.getId()).one();

    }


}