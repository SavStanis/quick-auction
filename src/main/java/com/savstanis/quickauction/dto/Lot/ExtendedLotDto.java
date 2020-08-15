package com.savstanis.quickauction.dto.Lot;

import com.savstanis.quickauction.dto.User.UserDto;
import com.savstanis.quickauction.model.Lot;
import com.savstanis.quickauction.model.LotStatus;
import lombok.Data;

@Data
public class ExtendedLotDto  extends  LotDto{

    private Long id;

    private LotStatus lotStatus;

    private UserDto seller;

    public static ExtendedLotDto fromLot(Lot lot) {
        ExtendedLotDto extendedLotDto = new ExtendedLotDto();

        extendedLotDto.setId(lot.getId());
        extendedLotDto.setName(lot.getName());
        extendedLotDto.setDescription(lot.getDescription());
        extendedLotDto.setPrice(lot.getPrice());
        extendedLotDto.setLotStatus(lot.getLotStatus());
        extendedLotDto.setSeller(UserDto.fromUser(lot.getSeller()));

        return extendedLotDto;
    }
}
