package com.isa.jjdzr.walletweb.dto;

import jakarta.validation.constraints.Min;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class BuyInfoDto {

    private String assetId;
    private Long walletId;
    private BigDecimal price;

    @Min(value = 0)
    private BigDecimal quantity;
}
