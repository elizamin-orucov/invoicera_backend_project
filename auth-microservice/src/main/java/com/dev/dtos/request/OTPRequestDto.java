package com.dev.dtos.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OTPRequestDto {
    private String mobile;
}
