package com.dev.dtos.OTP;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OTPRequestDto {
    private final String phoneNumber;
    private final String message;

    public OTPRequestDto(String phoneNumber, String message) {
        this.phoneNumber = phoneNumber;
        this.message = message;
    }
}
