package com.company.cakes.listeners;

import com.company.cakes.entity.Cake;
import com.haulmont.cuba.core.EntityManager;
import com.haulmont.cuba.core.app.UniqueNumbersAPI;
import com.haulmont.cuba.core.listener.BeforeInsertEntityListener;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
//@Listeners("cakes_CakeChangedListener")
@Component("cakes_CakeChangedListener")
public class CakeChangedListener  implements
        BeforeInsertEntityListener<Cake> {

    @Inject
    private Logger log;

    @Inject
    private UniqueNumbersAPI uniqueNumbers;



    @Override
    public void onBeforeInsert(Cake entity, EntityManager entityManager) {
        entity.setSku(uniqueNumbers.getNextNumber("sku"));
        //log.info("Variable editado = {}", entity.getSku());
    }
}
//Test git
//Test git 2
