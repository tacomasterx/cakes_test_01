package com.company.cakes.listeners;

import com.company.cakes.entity.Cake;
import com.haulmont.cuba.core.EntityManager;
import com.haulmont.cuba.core.app.UniqueNumbersAPI;
import com.haulmont.cuba.core.listener.BeforeInsertEntityListener;
//import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
//@Listeners("cakes_CakeChangedListener") //Esto se agrega a la Entity Cake
@Component("cakes_CakeChangedListener")
public class CakeChangedListener  implements
        BeforeInsertEntityListener<Cake> {

//    @Inject
//    private Logger log;  //Para enviar mensajes a la consola, borrar en el futuro.

    @Inject
    private UniqueNumbersAPI uniqueNumbers;



    @Override
    public void onBeforeInsert(Cake entity, EntityManager entityManager) {//Evento que ocurre justo antes de insertar elementos a la base de datos.
        entity.setSku(uniqueNumbers.getNextNumber("sku")); //Sentencia para modificar la celda a agregar
        //log.info("Variable editado = {}", entity.getSku());
    }
}
//Test git
//Test git 2
