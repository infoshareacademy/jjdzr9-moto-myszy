package com.isa.jjdzr.walletweb.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TopUpDto {
    @Min(value = 0, message = "Wartość nie może być ujemna")
    @Max(value = 1000000, message = "Zbyt duża wartość")
    @NotNull(message = "Pole nie może być puste")
    private BigDecimal cash;
}
