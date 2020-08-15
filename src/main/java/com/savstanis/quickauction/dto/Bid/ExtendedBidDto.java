package com.savstanis.quickauction.dto.Bid;

import com.savstanis.quickauction.dto.Lot.ExtendedLotDto;
import com.savstanis.quickauction.dto.User.UserDto;
import com.savstanis.quickauction.model.Bid;
import com.savstanis.quickauction.model.BidStatus;
import lombok.Data;

@Data
public class ExtendedBidDto extends BidDto {
    private Long id;
    private UserDto userDto;
    private BidStatus bidStatus;

    public static ExtendedBidDto fromBid(Bid bid) {
        ExtendedBidDto extendedBidDto = new ExtendedBidDto();

        extendedBidDto.setId(bid.getId());
        extendedBidDto.setValue(bid.getValue());
        extendedBidDto.setBidStatus(bid.getStatus());
        extendedBidDto.setUserDto(UserDto.fromUser(bid.getUser()));
        extendedBidDto.setLotDto(ExtendedLotDto.fromLot(bid.getLot()));

        return extendedBidDto;
    }
}