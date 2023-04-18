package com.isa.jjdzr.walletweb.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class BuyInfoDto {

    private String assetId;
    private String assetName;
    private Long walletId;
    private BigDecimal price;

    @Min(value = 0)
    @NotNull
    private BigDecimal quantity;
}
