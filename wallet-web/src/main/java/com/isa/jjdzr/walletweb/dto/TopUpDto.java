package com.isa.jjdzr.walletweb.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TopUpDto {
    @Min(value = 0, message = "Wartość nie może być ujemna")
    @Max(value = 1000000, message = "Zbyt duża wartość")
    @NotBlank(message = "Kwota nie może być pusta")
    //TODO: messenge when cannot create object (eg. when wrong input)
    private BigDecimal cash;
}
