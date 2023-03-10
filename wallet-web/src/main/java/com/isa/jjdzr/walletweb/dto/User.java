package com.isa.jjdzr.walletweb.dto;

import com.isa.jjdzr.walletweb.BasicEntity;
import com.isa.jjdzr.walletweb.ValidSymbols;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class User implements BasicEntity {
    private Long id;
    @NotBlank(message="Username cannot be blank")
    @Size(min=4, message="Username is too short")
    @ValidSymbols
    private String username;
    @NotBlank(message="Password cannot be blank")
    @Size(min=6, message="Password is too short")
    @ValidSymbols
    private String password;
    private String confirmPassword;


}
