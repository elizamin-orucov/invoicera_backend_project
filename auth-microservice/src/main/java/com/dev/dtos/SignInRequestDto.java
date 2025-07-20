package com.dev.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignInRequestDto {
    @NotBlank(message = "Number is required!")
    private String phoneNumber;

    @NotBlank(message = "Password is required!")
    private String password;

}
