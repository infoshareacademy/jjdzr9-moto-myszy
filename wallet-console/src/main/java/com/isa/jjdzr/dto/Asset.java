package com.isa.jjdzr.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
public class Asset {
    private String id;
    private BigDecimal currentPrice;

}
