package com.company.cakes.web.widgets;

import com.haulmont.addon.dashboard.web.annotation.DashboardWidget;
import com.haulmont.addon.dashboard.web.events.DashboardEvent;
import com.haulmont.addon.dashboard.web.widget.RefreshableWidget;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.gui.components.Label;
import com.haulmont.cuba.gui.screen.ScreenFragment;
import com.haulmont.cuba.gui.screen.Subscribe;
import com.haulmont.cuba.gui.screen.UiController;
import com.haulmont.cuba.gui.screen.UiDescriptor;
import org.slf4j.Logger;

import javax.inject.Inject;

@UiController("cakes_CakeTotalWidget")
@UiDescriptor("cake-total-widget.xml")
@DashboardWidget(name = "Cake Total")
public class CakeTotalWidget extends ScreenFragment implements RefreshableWidget {

    @Inject
    private Label<String> cakeTotalValue;
    @Inject
    private DataManager dataManager;
    //@Inject
   // private Logger log;

    @Subscribe
    public void onAfterInit(AfterInitEvent event) {
        cakeTotalValue.setValue(getTotalCakes().toString());
        //log.info("Variable foo = {}", getTotalCakes());
    }


    @Override
    public void refresh(DashboardEvent dashboardEvent) {
        cakeTotalValue.setValue(getTotalCakes().toString());
        //log.info("Variable foo = {}", getTotalCakes());
    }

    private Integer getTotalCakes(){
        return dataManager.loadValue("select count(e) from cakes_CakeInventory e", Integer.class).one();
    }
}