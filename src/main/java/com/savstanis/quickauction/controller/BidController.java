package com.savstanis.quickauction.controller;

import com.savstanis.quickauction.Routes;
import com.savstanis.quickauction.controller.response.ResponseEntityFactory;
import com.savstanis.quickauction.dto.Bid.CreationBidDto;
import com.savstanis.quickauction.dto.Bid.ExtendedBidDto;
import com.savstanis.quickauction.service.BidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = Routes.BID)
public class BidController {

    private final BidService bidService;

    @Autowired
    public BidController(BidService bidService) {
        this.bidService = bidService;
    }

    @PostMapping
    public ResponseEntity createBid(@RequestBody @Valid CreationBidDto bidDto, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                return ResponseEntityFactory.getErrorResponse(
                        bindingResult.getFieldError().getField(),
                        bindingResult.getFieldError().getDefaultMessage()
                );
            }

            return ResponseEntityFactory
                    .getSuccessResponse("bid", ExtendedBidDto.fromBid(bidService.createBid(bidDto)));
        } catch (Exception e) {
            return ResponseEntityFactory.getErrorResponse(e.getMessage());
        }
    }
}
