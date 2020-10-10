package com.company.cakes.web.screens.cake;

import com.haulmont.cuba.gui.components.Filter;
import com.haulmont.cuba.gui.screen.*;
import com.company.cakes.entity.Cake;
import org.slf4j.Logger;

import javax.inject.Inject;

@UiController("cakes_Cake.browse")
@UiDescriptor("cake-browse.xml")
@LookupComponent("cakesTable")
@LoadDataBeforeShow
public class CakeBrowse extends StandardLookup<Cake> {
    @Inject
    private Filter filter;
    @Inject
    private Logger log;

    @Install(to = "filter", subject = "afterFilterAppliedHandler")



    private void filterAfterFilterAppliedHandler() {
        String parameter =  filter.getParamValue("cakes_").toString();
        log.info("Variable foo = {}", parameter);
    }
}