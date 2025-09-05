package com.business_data_service.dtos.invoice;

import com.business_data_service.base.BaseIdDto;
import com.business_data_service.dtos.category.CategoryListDto;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class InvoiceDetailDto extends BaseIdDto {

    private String recipientName;

    private String TIN;

    private String type;

    private String series;

    private String number;

    private String groupName;

    private String name;

    private String code;

    private String unit;

    private BigDecimal unitPrice;

    private BigDecimal quantity;

    private BigDecimal totalPrice;

    private BigDecimal exciseDegree;

    private BigDecimal excisePrice;

    private BigDecimal roadTax;

    private String identificationNumber;

    private BigDecimal VAT18Percent;

    private BigDecimal VAT0Percent;

    private BigDecimal VATExempt;
}
