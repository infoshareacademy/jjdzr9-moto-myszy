package com.isa.jjdzr.dto;

import com.isa.jjdzr.common.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Wallet implements BaseEntity {
    private Long id;
    private Long userId;
    private String walletName;
    private BigDecimal cash;
}
