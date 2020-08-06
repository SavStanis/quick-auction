package com.savstanis.quickauction.controller;

import com.savstanis.quickauction.domain.Lot;
import com.savstanis.quickauction.repo.LotRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("lot")
public class LotController {

    private final LotRepo lotRepo;

    @Autowired
    public LotController(LotRepo lotRepo) {
        this.lotRepo = lotRepo;
    }

    @GetMapping
    public List<Lot> getAll () {
        return lotRepo.findAll();
    }

    @GetMapping("{id}")
    public Lot getOne(@PathVariable("id") Lot lot) {
        return lot;
    }

    @PostMapping
    public Lot create(@RequestBody Lot lot) {
        lot.setCreationDate(LocalDateTime.now());
        return lotRepo.save(lot);
    }

    @PutMapping("{id}")
    public Lot update(@PathVariable("id") Lot lotFromDb, @RequestBody Lot lot) {
        BeanUtils.copyProperties(lot, lotFromDb, "id");
        return lotRepo.save(lotFromDb);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Lot lot) {
        lotRepo.delete(lot);
    }
}
