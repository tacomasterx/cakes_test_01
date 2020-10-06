package com.company.cakes.listeners;

import com.company.cakes.entity.Cake;
import com.company.cakes.entity.PriceGroup;
import com.haulmont.cuba.core.EntityManager;
import com.haulmont.cuba.core.app.UniqueNumbersAPI;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.listener.BeforeInsertEntityListener;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
//@Listeners("cakes_CakeChangedListener") //Esto se agrega a la Entity Cake
@Component("cakes_CakeChangedListener")
public class CakeChangedListener  implements
        BeforeInsertEntityListener<Cake> {

    @Inject
    private Logger log;  //Para enviar mensajes a la consola, borrar en el futuro.

    @Inject
    private UniqueNumbersAPI uniqueNumbers;
    @Inject
    private DataManager dataManager;

    @Override
    public void onBeforeInsert(Cake entity, EntityManager entityManager) {//Evento que ocurre justo antes de insertar elementos a la base de datos.
        entity.setSku(uniqueNumbers.getNextNumber("sku")); //Sentencia para modificar la celda a agregar
        //log.info("Variable editado = {}", entity.getSku());
        Long next_number = uniqueNumbers.getNextNumber("group_sku");
        log.info("Variable editado = {}", next_number );
        entity.setGroup_sku( "" + getAbbreviation(entity.getPriceGroup()) + getZeroFill(next_number) + next_number.toString() );
        log.info("Variable editado = {}", "" + getAbbreviation(entity.getPriceGroup()) + getZeroFill(next_number) + next_number.toString() );
    }

    private String getAbbreviation(PriceGroup price_group){
        String string = dataManager.loadValue("select e.name from cakes_PriceGorup e where e.id = :priceGroup", String.class).parameter("priceGroup", price_group.getId()).one();
        return string.substring(0,3);
    }

    private String getZeroFill(Long number){
        if (number < 10){
            return "000";
        } else if (number < 100){
            return "00";
        } else if (number < 1000){
            return "0";
        } else {
            return "";
        }
    }
}
//Test git
//Test git 2
