package com.savstanis.quickauction.controller;

import com.savstanis.quickauction.Routes;
import com.savstanis.quickauction.controller.response.ResponseEntityFactory;
import com.savstanis.quickauction.dto.Lot.ExtendedLotDto;
import com.savstanis.quickauction.dto.Lot.LotDto;
import com.savstanis.quickauction.model.Lot;
import com.savstanis.quickauction.service.LotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = Routes.LOT)
public class LotController {

    private final LotService lotService;

    @Autowired
    public LotController(LotService lotService) {
        this.lotService = lotService;
    }

    @PostMapping
    public ResponseEntity createLot(
            @RequestBody @Valid LotDto lotDto,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return ResponseEntityFactory.getErrorResponse(
                    bindingResult.getFieldError().getField(),
                    bindingResult.getFieldError().getDefaultMessage()
            );
        }

        try {
            ExtendedLotDto responseBody = ExtendedLotDto.fromLot(lotService.createLot(lotDto.toLot()));
            return  ResponseEntityFactory.getSuccessResponse("lot", responseBody);
        } catch (Exception e) {
            return ResponseEntityFactory.getErrorResponse(e.getMessage());
        }
    }

    @GetMapping(value = "/{lot_id}")
    public ResponseEntity getActiveById(
            @PathVariable(name = "lot_id") Long lotId
    ) {
        try {
            return ResponseEntityFactory.getSuccessResponse(
                    "lot",
                    ExtendedLotDto.fromLot(lotService.findActiveById(lotId))
            );
        } catch (Exception e) {
            return ResponseEntityFactory.getErrorResponse(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity getAllActive() {
        try {
            List<Lot> active = lotService.getActive();
            List<ExtendedLotDto> responseBody = active.stream()
                    .map(ExtendedLotDto::fromLot)
                    .collect(Collectors.toList());

            return ResponseEntityFactory.getSuccessResponse("lots", responseBody);
        } catch (Exception e) {
            return ResponseEntityFactory.getErrorResponse(e.getMessage());
        }
    }

}