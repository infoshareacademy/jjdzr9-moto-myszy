package com.isa.jjdzr.walletcore.dto;


import com.isa.jjdzr.walletcore.common.BaseEntity;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotBlank(message = "Nazwa porfela nie może być pusta")
    //TODO: add symbol validation (only digits and numbers)
    private String walletName;

    @Min(value = 0, message = "Wartość nie może być ujemna")
    @Max(value = 1000000, message = "Zbyt duża wartość")
    @NotNull(message = "Kwota nie może być pusta")
    private BigDecimal cash;
}
