package com.dev.dtos.OTP;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SmsVerifyRequest {
    private String code;
    private String phoneNumber;
}
