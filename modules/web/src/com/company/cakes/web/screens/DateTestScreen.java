package com.company.cakes.web.screens;

import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.screen.Screen;
import com.haulmont.cuba.gui.screen.Subscribe;
import com.haulmont.cuba.gui.screen.UiController;
import com.haulmont.cuba.gui.screen.UiDescriptor;
import org.slf4j.Logger;

import javax.inject.Inject;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@UiController("cakes_DateTestScreen")
@UiDescriptor("date-test-screen.xml")
public class DateTestScreen extends Screen {

    @Inject
    private DataManager dataManager;
    @Inject
    private Logger log;

    @Subscribe("btnShowDate")
    public void onBtnShowDateClick(Button.ClickEvent event) {

//        Date date = dataManager.loadValue("select e.createTs from cakes_Cake e", Date.class).one();
//        log.info("Variable date = {}", date );
//        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//        String smolDate = "" + localDate.getYear() ;
//        log.info("Variable localDate = {}", "" + localDate.getDayOfMonth() + localDate.getMonthValue() + smolDate.substring(2) );
       
        LocalDate localDate = LocalDate.now();
        String smolYear = "" + localDate.getYear() ;
        //log.info("Variable localDate = {}", "" + localDate.getDayOfMonth() + localDate.getMonthValue() + smolDate.substring(2) );
        String smolDate = "" + dateZerofill(localDate.getDayOfMonth()) + dateZerofill(localDate.getMonthValue()) + smolYear.substring(2);
        log.info("Variable localDate = {}", smolDate);
    }

    private String dateZerofill(Integer number){
        if (number > 9) {
            return "" + number;
        }
        return "0" + number;
    }
}