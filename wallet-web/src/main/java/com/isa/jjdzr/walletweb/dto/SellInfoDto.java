package com.isa.jjdzr.walletweb.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class SellInfoDto {

    private Long walletAssetId;

    @Min(value = 0)
    @NotNull
    private BigDecimal quantityToSell;
}
