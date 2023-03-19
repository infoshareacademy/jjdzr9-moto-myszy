package com.isa.jjdzr.dto;

import com.isa.jjdzr.common.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
@Data
public class Wallet implements BaseEntity {
    private Long id;
    private Long userId;
    private String walletName;
    private BigDecimal cash;

    public Wallet() {

    }

}
