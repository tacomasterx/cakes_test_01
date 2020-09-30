package com.company.cakes.service;

import org.springframework.stereotype.Service;

@Service(CountPriceGroupsService.NAME)
public class CountPriceGroupsServiceBean implements CountPriceGroupsService {
    @Override
    public String helloWorld() {
        return "Hello world!";
    }
}