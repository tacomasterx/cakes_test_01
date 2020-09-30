package com.company.cakes.web.screens.refrigerator;

import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.screen.*;
import com.company.cakes.entity.Refrigerator;
import org.slf4j.Logger;

import javax.inject.Inject;


@UiController("cakes_RefrigeratorByStore.browse")
@UiDescriptor("refrigerator-by-store-browse.xml")
@LookupComponent("refrigeratorsTable")
@LoadDataBeforeShow
public class RefrigeratorByStoreBrowse extends StandardLookup<Refrigerator> implements ScreenOptions {



    @Inject
    private Logger log;
    @Inject
    private CollectionContainer<Refrigerator> refrigeratorsDc;

    @Subscribe
    public void onInit(InitEvent event) {
        log.info("Variable foo = {}", "Init event");
    }

    
}