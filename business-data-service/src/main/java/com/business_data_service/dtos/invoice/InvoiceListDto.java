package com.business_data_service.dtos.invoice;

import com.business_data_service.base.BaseDto;
import com.business_data_service.base.BaseIdDto;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class InvoiceListDto extends BaseDto {
    private String recipientName;
    private String TIN;
    private String type;
    private String series;
    private String number;
    private String groupName;
    private String name;
    private String code;
    private String identificationNumber;
    private String unit;
    private BigDecimal quantity;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;
    // new add
    private BigDecimal excisePrice;
    private BigDecimal roadTax;
    private BigDecimal VAT0Percent;
    private BigDecimal VATExempt;
    private BigDecimal VATIncluded;

}
