package com.company.cakes.service;

import org.springframework.stereotype.Service;

//Bean inútil creado para intentar algo que no terminó por funcionar, pero borrarlo me causa errores.

@Service(CountPriceGroupsService.NAME)
public class CountPriceGroupsServiceBean implements CountPriceGroupsService {
    @Override
    public String helloWorld() {
        return "Hello world!";
    }
}