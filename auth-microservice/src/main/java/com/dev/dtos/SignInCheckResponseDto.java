package com.dev.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignInCheckResponseDto {
    private String userName;
    private boolean success;
    private String message;
}
