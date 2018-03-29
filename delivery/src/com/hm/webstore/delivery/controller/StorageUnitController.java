package com.hm.webstore.delivery.controller;

import com.hm.webstore.domain.actions.StorageUnitActions;
import com.hm.webstore.domain.gateway.StorageUnitGateway;
import com.hm.webstore.domain.presenter.StorageUnitPresenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
public class StorageUnitController extends StorageUnitActions {
    @Autowired
    public StorageUnitController(StorageUnitPresenter presenter,
                                 StorageUnitGateway gateway) {
        super(presenter, gateway);
    }
    
    
    @PostMapping("/storageUnits")
    public Object createStorageUnit(@RequestParam("name") String name) {
        return super.createStorageUnit(name).toString();
    }
}
