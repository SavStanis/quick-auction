package com.savstanis.quickauction.service;

import com.savstanis.quickauction.repository.LotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LotService {

    private final LotRepository lotRepository;

    @Autowired
    public LotService(LotRepository lotRepository) {
        this.lotRepository = lotRepository;
    }
}
