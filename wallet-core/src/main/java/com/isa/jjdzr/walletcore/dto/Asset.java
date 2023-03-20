package com.isa.jjdzr.walletcore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Asset {
    private String id;
    private BigDecimal currentPrice;

}
