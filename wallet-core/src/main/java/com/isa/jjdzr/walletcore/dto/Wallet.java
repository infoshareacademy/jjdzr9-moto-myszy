package com.isa.jjdzr.walletcore.dto;


import com.isa.jjdzr.walletcore.common.BaseEntity;
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
