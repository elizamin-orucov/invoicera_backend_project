package com.business_data_service.models;

import com.business_data_service.base.BaseEntity;
import com.business_data_service.models.enums.MeasurementUnit;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;


@Setter
@Getter
@Entity
@Table(name = "invoice")
public class InvoiceEntity extends BaseEntity {

//    @NotNull(message = "Recipient name must not be null")
    @Column(name = "recipient_name", nullable = true)
    private String recipientName;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id", nullable = true)
    private ProductEntity product;

//    @NotNull(message = "TIN must not be null")
    @Column(name = "TIN", nullable = true)
    private String TIN;

//    @NotNull(message = "Type must not be null")
    @Column(name = "type", nullable = true)
    private String type;

//    @NotNull(message = "Series must not be null")
    @Column(name = "series", nullable = true)
    private String series;

//    @NotNull(message = "Number must not be null")
    @Column(name = "number", nullable = true)
    private String number;

//    @NotNull(message = "Code unit cannot be null")
    @Column(name = "code", nullable = true)
    private String code;

//    @NotNull(message = "Name unit cannot be null")
    @Column(name = "group_name", nullable = true)
    private String groupName;

    /**
     * The unit of measurement for the invoiced item.
     * Example: LITER, KILOGRAM, METER, etc.
     */
//    @NotNull(message = "Measurement unit cannot be null")
    @Enumerated(EnumType.STRING)
    @Column(name = "unit", nullable = true)
    private MeasurementUnit unit;

    /**
     * Price per single unit.
     * Example: 1 liter costs 1.20 AZN
     */
//    @NotNull(message = "Unit price cannot be null")
//    @DecimalMin(value = "0.0", inclusive = false, message = "Unit price must be greater than 0")
    @Column(name = "unit_price", nullable = true, precision = 19, scale = 4)
    private BigDecimal unitPrice;

    /**
     * Quantity of units purchased or invoiced.
     * Example: 50 liters of fuel.
     */
//    @NotNull(message = "Quantity cannot be null")
//    @DecimalMin(value = "0.0", inclusive = false, message = "Quantity must be greater than 0")
    @Column(name = "quantity", nullable = true, precision = 19, scale = 4)
    private BigDecimal quantity;

    /**
     * Total price = unitPrice * quantity.
     * Calculated or stored directly.
     */
    @Column(name = "total_price", nullable = true, precision = 19, scale = 4)
    private BigDecimal totalPrice;

    /**
     * Excise degree (customs tax percentage or value).
     * Example: 10%, stored as 10.0
     */
//    @NotNull(message = "Excise degree cannot be null")
//    @DecimalMin(value = "0.0", message = "Excise degree cannot be negative")
    @Column(name = "excise_degree", nullable = true, precision = 5, scale = 2)
    private BigDecimal exciseDegree;
    // cixart , edv celb edilen yaz
    //  edv meblegi yaz

    /**
     * Total excise tax amount applied to this invoice.
     * Example: totalPrice * (exciseDegree / 100)
     */
    @Column(name = "excise_price", nullable = true, precision = 19, scale = 4)
    private BigDecimal excisePrice;

    /**
     * Road tax value associated with this invoice.
     * Applied if the product affects transportation.
     */
//    @DecimalMin(value = "0.0", message = "Road tax cannot be negative")
    @Column(name = "road_tax", precision = 19, scale = 4)
    private BigDecimal roadTax;

    /**
     * Unique identification number of the invoice.
     * Could be a barcode, serial number, or registration code.
     */
//    @NotBlank(message = "Identification number cannot be blank")
//    @Size(max = 100, message = "Identification number cannot exceed 100 characters")
    @Column(name = "identification_number", nullable = true, length = 100)
    private String identificationNumber;

    /**
     * VAT amount calculated at 18% rate.
     * Applied to goods and services subject to standard VAT rate.
     */
//    @NotNull(message = "VAT 18% cannot be null")
//    @DecimalMin(value = "0.0", message = "VAT 18% cannot be negative")
    @Column(name = "VAT_18_percent", nullable = true, precision = 19, scale = 4)
    private BigDecimal VAT18Percent;

    /**
     * VAT amount calculated at 0% rate.
     * Applied to goods and services with zero VAT rate (exports, etc.).
     */
//    @NotNull(message = "VAT 0% cannot be null")
//    @DecimalMin(value = "0.0", message = "VAT 0% cannot be negative")
    @Column(name = "VAT_0_percent", nullable = true, precision = 19, scale = 4)
    private BigDecimal VAT0Percent;

    /**
     * VAT exempt amount.
     * Applied to goods and services that are exempt from VAT.
     */
//    @NotNull(message = "VAT exempt cannot be null")
//    @DecimalMin(value = "0.0", message = "VAT exempt cannot be negative")
    @Column(name = "VAT_exempt", nullable = true, precision = 19, scale = 4)
    private BigDecimal VATExempt;
}
