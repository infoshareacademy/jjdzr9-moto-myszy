package com.isa.jjdzr.walletweb.dto;

import com.isa.jjdzr.walletcore.common.BaseEntity;
import com.isa.jjdzr.walletcore.common.ValidSymbols;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class User implements BaseEntity {
    private Long id;
    @NotBlank(message="Nazwa użytkownika nie może być pusta")
    @Size(min=4, message="Nazwa użytkownika jest zbyt krótka")
    @ValidSymbols
    private String username;
    @NotBlank(message="Hasło nie może być puste")
    @Size(min=6, message="Hasło jest za krótkie")
    @ValidSymbols
    private String password;
    private String confirmPassword;

}
