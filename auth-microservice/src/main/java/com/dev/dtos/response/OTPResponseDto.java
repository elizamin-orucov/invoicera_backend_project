package com.dev.dtos.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OTPResponseDto {
    private String message;
    private boolean success;
    private String number;
}
