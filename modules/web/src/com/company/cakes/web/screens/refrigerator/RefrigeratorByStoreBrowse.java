package com.company.cakes.web.screens.refrigerator;

// Screen con parámetro en su busqueda (ver data container en el apartado xml)
// al no lograr modificar el sql del container ni modificar la tabla antes de su despliegue
// me vi en la necesidad de crear esta screen adicional para solo desplegar ciertos refris.

import com.company.cakes.entity.Refrigerator;
import com.company.cakes.entity.Store;
import com.company.cakes.web.screens.RefrigeratorBrowseOptions;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.screen.*;
//import org.slf4j.Logger;

import javax.inject.Inject;


@UiController("cakes_RefrigeratorByStore.browse")
@UiDescriptor("refrigerator-by-store-browse.xml")
@LookupComponent("refrigeratorsTable")
@LoadDataBeforeShow
public class RefrigeratorByStoreBrowse extends StandardLookup<Refrigerator> implements ScreenOptions { // Implementar para poder trabajar con options



//    @Inject
//    private Logger log;
    @Inject
    private CollectionLoader<Refrigerator> refrigeratorsDl;// Loader de la colección de refrigeradores

    // Intento futil por modificar el SQL de la colección
    //    private void createRefrigeratorLoader(CollectionContainer<Refrigerator> container, Store store) {
//        CollectionLoader<Refrigerator> loader = dataComponents.createCollectionLoader();
//        loader.setQuery("select e from cakes_Refrigerator e where e.store.id = "+store.getId()+" ");
//        loader.setContainer(container);
//        loader.setDataContext(getScreenData().getDataContext());
//    }

    private Store store; // Variable local para operar entre eventos.

    @Subscribe
    public void onInit(InitEvent event) { // Las screen options del picker se pasan a este evento.
        ScreenOptions options = event.getOptions();
        if (options instanceof RefrigeratorBrowseOptions) { // Verificar que las options sean de la clase requerida
            Store store = ((RefrigeratorBrowseOptions) options).getStore();
            //log.info("Variable foo = {}", store.getName());
            this.store = store;
        }
        
        
    }

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) { //Evento antes de desplegar información en pantalla
        if (store == null)
            throw new IllegalStateException("Store parameter is null"); //Mensaje de error en caso de que no haya options.
        refrigeratorsDl.setParameter("store", store); // Se le da valor al parámetro del DataLoader
        refrigeratorsDl.load();
    }



    
}