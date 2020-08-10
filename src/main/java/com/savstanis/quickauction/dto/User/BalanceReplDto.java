package com.savstanis.quickauction.dto.User;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BalanceReplDto {

    @Positive
    @NotNull
    private Integer sum;
}
