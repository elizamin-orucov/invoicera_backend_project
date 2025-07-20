package com.dev.service.impl;

import com.dev.dtos.*;
import com.dev.dtos.OTP.SmsVerifyRequest;
import com.dev.exceptions.RoleNotFoundException;
import com.dev.exceptions.UserAlreadyExistsException;
import com.dev.data.RoleFactory;
import com.dev.models.Role;
import com.dev.models.User;
import com.dev.security.UserDetailsImpl;
import com.dev.security.jwt.JwtUtils;
import com.dev.service.AuthService;
import com.dev.service.UserService;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import com.dev.service.impl.otp.SmsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    private final RoleFactory roleFactory;

    private final AuthenticationManager authenticationManager;

    private final JwtUtils jwtUtils;

    private final SmsService smsService;

    @Override
    public ApiResponseDto<?> signUpUser(SignUpRequestDto signUpRequestDto)
            throws UserAlreadyExistsException, RoleNotFoundException {
//        if (userService.existsByUserName(signUpRequestDto.getUserName())) {
//            throw new UserAlreadyExistsException("Registration Failed: Provided email already exists. Try sign in or provide another email.");
//        }
        if (userService.existsByUsername(signUpRequestDto.getPhoneNumber())) {
            throw new UserAlreadyExistsException("Registration Failed: Provided username already exists. Try sign in or provide another username.");
        }

        User user = createUser(signUpRequestDto);
        userService.save(user);
        return
                ApiResponseDto.builder()
                        .isSuccess(true)
                        .message("User account has been successfully created!")
                        .build();
    }

    @Override
    public ApiResponseDto<?> checkUserByUserName(SignInCheckDto checkDto) {
        boolean exists = userService.existsByUsername(checkDto.getPhoneNumber());
        if (exists) {
            String otpCode = generateOtp();

            smsService.sendOtp(checkDto.getPhoneNumber(), otpCode);

            SmsVerifyRequest smsRequest = SmsVerifyRequest.builder()
                    .code(otpCode)
                    .phoneNumber(checkDto.getPhoneNumber())
                    .build();

            userService.updatePassword(checkDto.getPhoneNumber(), passwordEncoder.encode(otpCode));

//            smsVerifyService.verifySms(smsRequest);

            SignInCheckResponseDto response = SignInCheckResponseDto.builder()
                    .success(true)
                    .userName(checkDto.getPhoneNumber())
                    .message("success")
                    .build();

            return ApiResponseDto.builder()
                    .isSuccess(true)
                    .response(response)
                    .message("success")
                    .httpStatus(HttpStatus.OK)
                    .build();
        } else {
            return ApiResponseDto.builder()
                    .isSuccess(false)
                    .message("user not found")
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .build();
        }
    }


    @Override
    public ApiResponseDto<?> signInUser(SignInRequestDto signInRequestDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequestDto.getPhoneNumber(), signInRequestDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        SignInResponseDto signInResponseDto = SignInResponseDto.builder()
                .username(userDetails.getUsername())
                .email(userDetails.getEmail())
                .id(userDetails.getId())
                .token(jwt)
                .roles(roles)
                .build();

        return ApiResponseDto.builder()
                        .isSuccess(true)
                        .message("Sign in successfull!")
                        .response(signInResponseDto)
                        .build();
    }

    private User createUser(SignUpRequestDto signUpRequestDto) throws RoleNotFoundException {
        return User.builder()
//                .email(signUpRequestDto.getEmail())
                .username(signUpRequestDto.getPhoneNumber())
                .password(passwordEncoder.encode(signUpRequestDto.getPassword()))
                .enabled(true)
                .roles(determineRoles(signUpRequestDto.getRoles()))
                .build();
    }

    private Set<Role> determineRoles(Set<String> strRoles) throws RoleNotFoundException {
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            roles.add(roleFactory.getInstance("user"));
        } else {
            for (String role : strRoles) {
                roles.add(roleFactory.getInstance(role));
            }
        }
        return roles;
    }

    private String generateOtp() {
        return String.valueOf(new Random().nextInt(9000) + 1000);
    }

}