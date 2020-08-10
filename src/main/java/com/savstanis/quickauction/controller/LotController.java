package com.savstanis.quickauction.controller;

import com.savstanis.quickauction.Routes;
import com.savstanis.quickauction.service.LotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = Routes.LOT)
public class LotController {

    private final LotService lotService;

    @Autowired
    public LotController(LotService lotService) {
        this.lotService = lotService;
    }
}
