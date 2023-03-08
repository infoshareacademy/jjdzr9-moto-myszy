package com.isa.jjdzr.walletweb;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class User {
    @NotBlank(message="Username cannot be blank")
    @Size(min=4, message="Username is too short")
    private String username;
    @NotBlank(message="Password cannot be blank")
    @Size(min=6, message="Password is too short")
    private String password;


}
