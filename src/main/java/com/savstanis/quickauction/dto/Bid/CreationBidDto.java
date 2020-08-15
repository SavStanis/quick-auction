package com.savstanis.quickauction.dto.Bid;

import com.savstanis.quickauction.model.Bid;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class CreationBidDto {

    @NotNull(message = "Value must be positive")
    @Positive(message = "Value must be positive")
    private Integer value;

    @NotNull
    private Long lotId;
}
