package com.company.cakes.web.screens;

//Screen que solo existe para manejar ScreenOptions, tratar de borrar u omitir del menu para no ser vista por el usuario final.


import com.company.cakes.entity.Store;
import com.haulmont.cuba.gui.screen.Screen;
import com.haulmont.cuba.gui.screen.ScreenOptions;
import com.haulmont.cuba.gui.screen.UiController;
import com.haulmont.cuba.gui.screen.UiDescriptor;

@UiController("cakes_RefrigeratorBrowseOptions")
@UiDescriptor("refrigerator-browse-options.xml")
public class RefrigeratorBrowseOptions extends Screen implements ScreenOptions { // Se deben implementar las screenoptions para que la clase sirva de interfaz

    private Store store;

    public RefrigeratorBrowseOptions(Store store){
        this.store = store;
    } //Constructor, este será utilizado para guardar las options.

    public Store getStore() {
        return store;
    }//Getter que se usará al llamar a las Screen options

}