package com.business_data_service.models;


import com.business_data_service.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
@Entity
@Table(name = "automatic_invoice")
public class InvoiceAutomaticEntity extends BaseEntity {
    @Column(nullable = false, name = "customer")
    private String customer;

    @Column(nullable = false, name = "product")
    private String product;

    @Column(nullable = false, name = "price")
    private BigDecimal price;

    @Column(nullable = false, name = "date")
    private LocalDate date;
}

