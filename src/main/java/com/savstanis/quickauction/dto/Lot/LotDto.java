package com.savstanis.quickauction.dto.Lot;

import com.savstanis.quickauction.model.Lot;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;


@Data
@NoArgsConstructor
public class LotDto {

    @NotNull
    @Size(min = 10, max = 300)
    private String name;

    @NotNull
    @Size(min = 10, max = 1000)
    private String description;

    @Positive
    @NotNull
    private Integer price;

    public Lot toLot() {
        Lot lot = new Lot();

        lot.setName(name);
        lot.setDescription(description);
        lot.setPrice(price);

        return lot;
    }
}
