package com.company.cakes.web.screens.refrigerator;

import com.company.cakes.entity.Refrigerator;
import com.company.cakes.entity.Store;
import com.company.cakes.web.screens.RefrigeratorBrowseOptions;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.screen.*;
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
    private CollectionLoader<Refrigerator> refrigeratorsDl;

    //    private void createRefrigeratorLoader(CollectionContainer<Refrigerator> container, Store store) {
//        CollectionLoader<Refrigerator> loader = dataComponents.createCollectionLoader();
//        loader.setQuery("select e from cakes_Refrigerator e where e.store.id = "+store.getId()+" ");
//        loader.setContainer(container);
//        loader.setDataContext(getScreenData().getDataContext());
//    }

    private Store store;

    @Subscribe
    public void onInit(InitEvent event) {
        ScreenOptions options = event.getOptions();
        if (options instanceof RefrigeratorBrowseOptions) {
            Store store = ((RefrigeratorBrowseOptions) options).getStore();
            log.info("Variable foo = {}", store.getName());
            this.store = store;
          // refrigeratorList = dataManager.loadValue("select e.name from cakes_Refrigerator e", Refrigerator.class).list();
           // refrigeratorsTable;
        }
        
        
    }

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        if (store == null)
            throw new IllegalStateException("country parameter is null");
        refrigeratorsDl.setParameter("store", store);
        refrigeratorsDl.load();
    }



    
}