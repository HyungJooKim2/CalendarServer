package com.project.calendar.api.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.beans.ConstructorProperties;

@Data
public class LoginReq  {
    //@NotBlank
    private final String email;
    //@NotBlank
    private final String password;

    @ConstructorProperties({"email","password"})
    public LoginReq(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
