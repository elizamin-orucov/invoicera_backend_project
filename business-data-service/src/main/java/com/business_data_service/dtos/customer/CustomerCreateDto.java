package com.business_data_service.dtos.customer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CustomerCreateDto {

    @NotBlank(message = "Customer name must not be blank.")
    @Size(max = 50, message = "Customer name must not exceed 50 characters.")
    private String customerName;

//    @NotBlank(message = "Customer surname must not be blank.")
//    @Size(max = 50, message = "Customer surname must not exceed 50 characters.")
//    private String customerSurname;

    @NotBlank(message = "Contact information must not be blank.")
    @Size(max = 20, message = "Contact information must not exceed 20 characters.")
    private String contact;

//    @NotBlank(message = "Position must not be blank.")
//    @Size(max = 100, message = "Position must not exceed 100 characters.")
//    private String position;

    @NotBlank(message = "mail must not be blank.")
    @Size(max = 100, message = "mail must not exceed 100 characters.")
    private String mail;

    @NotBlank(message = "Fs code must not be blank.")
    @Size(max = 100, message = "fs code must not exceed 100 characters.")
    private String FSCode;

//    @NotBlank(message = "Institution name must not be blank.")
//    @Size(max = 100, message = "Institution name must not exceed 100 characters.")
//    private String institution;

    @NotBlank(message = "TIN must not be blank.")
    @Pattern(
            regexp = "^\\d{10}$",
            message = "TIN must be exactly 10 digits."
    )
    private String TIN;
}


