package com.dev.dtos;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class ApiResponseDto<T> {
    private boolean isSuccess;
    private String message;
    private T response;
    private HttpStatus httpStatus;
}