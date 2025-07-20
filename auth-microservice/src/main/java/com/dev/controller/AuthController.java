package com.dev.controller;

import com.dev.dtos.*;
import com.dev.service.AuthService;
import com.dev.exceptions.RoleNotFoundException;
import com.dev.exceptions.UserAlreadyExistsException;
import com.dev.security.jwt.JwtUtils;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final JwtUtils jwtUtils;

    @PostMapping("/signup")
    public ApiResponseDto<?> signUpUser(@RequestBody @Valid SignUpRequestDto signUpRequestDto)
            throws UserAlreadyExistsException, RoleNotFoundException {
        return authService.signUpUser(signUpRequestDto);
    }

    @PostMapping("/signin")
    public ApiResponseDto<?> signInUser(@RequestBody @Valid SignInRequestDto signInRequestDto){
        return authService.signInUser(signInRequestDto);
    }

    @PostMapping("/checkUserByUsername")
    public ResponseEntity<ApiResponseDto<?>> checkUserByEmail(@RequestBody SignInCheckDto checkDto){
        ApiResponseDto<?> response = authService.checkUserByUserName(checkDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/roles")
    public RolesResponseDto getUserRolesFromJwtToken(@RequestHeader("Authorization") String tokenHeader) {
        String token = tokenHeader.substring(7);
        List<String> roles = jwtUtils.getUserRolesFromJwtToken(token);
        return new RolesResponseDto(roles);
    }

}
