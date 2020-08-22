package com.savstanis.quickauction.dto.Lot;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.savstanis.quickauction.dto.User.UserDto;
import com.savstanis.quickauction.model.Lot;
import com.savstanis.quickauction.model.LotStatus;
import lombok.Data;

import java.util.Date;

@Data
public class ExtendedLotDto  extends  LotDto{

    private Long id;

    private LotStatus lotStatus;

    private UserDto seller;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private Date expired;

    public static ExtendedLotDto fromLot(Lot lot) {
        ExtendedLotDto extendedLotDto = new ExtendedLotDto();

        extendedLotDto.setId(lot.getId());
        extendedLotDto.setName(lot.getName());
        extendedLotDto.setDescription(lot.getDescription());
        extendedLotDto.setPrice(lot.getPrice());
        extendedLotDto.setLotStatus(lot.getLotStatus());
        extendedLotDto.setSeller(UserDto.fromUser(lot.getSeller()));
        extendedLotDto.setExpired(lot.getFinishDate());

        return extendedLotDto;
    }
}
