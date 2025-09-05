package com.business_data_service.dtos.invoice;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Setter
@Getter
public class InvoiceCreateDto {

    @NotNull(message = "Recipient name must not be null")
    private String recipientName;

    @NotBlank(message = "Product ID must not be blank")
    private String productID;

    @NotNull(message = "TIN must not be null")
    private String TIN;

    @NotNull(message = "Type must not be null")
    private String type;

    @NotNull(message = "Series must not be null")
    private String series;

    @NotNull(message = "Number must not be null")
    private String number;

    @NotNull(message = "Group Name must not be null")
    private String groupName;

//    @NotNull(message = "Code must not be null")
    private String code;

    @NotNull(message = "Unit must not be null")
    private String unitLabel;

    @NotNull(message = "Unit price must not be null")
    @DecimalMin(value = "0.0", message = "Unit price must be zero or positive")
    private BigDecimal unitPrice;

    @NotNull(message = "Quantity must not be null")
    @DecimalMin(value = "0.0", message = "Quantity must be zero or positive")
    private BigDecimal quantity;

    @NotNull(message = "Total price must not be null")
    @DecimalMin(value = "0.0", message = "Total price must be zero or positive")
    private BigDecimal totalPrice;

    @NotNull(message = "Excise degree must not be null")
    @DecimalMin(value = "0.0", message = "Excise degree must be zero or positive")
    private BigDecimal exciseDegree;

    @NotNull(message = "Excise price must not be null")
    @DecimalMin(value = "0.0", message = "Excise price must be zero or positive")
    private BigDecimal excisePrice;

    @NotNull(message = "Road tax must not be null")
    @DecimalMin(value = "0.0", message = "Road tax must be zero or positive")
    private BigDecimal roadTax;

    @NotBlank(message = "Identification number must not be blank")
    private String identificationNumber;

    @NotNull(message = "VAT 18% must not be null")
    @DecimalMin(value = "0.0", message = "VAT 18% must be zero or positive")
    private BigDecimal VAT18Percent;

    @NotNull(message = "VAT 0% must not be null")
    @DecimalMin(value = "0.0", message = "VAT 0% must be zero or positive")
    private BigDecimal VAT0Percent;

    @NotNull(message = "VAT exempt must not be null")
    @DecimalMin(value = "0.0", message = "VAT exempt must be zero or positive")
    private BigDecimal VATExempt;
}

