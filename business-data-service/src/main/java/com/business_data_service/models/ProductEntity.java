package com.business_data_service.models;

import com.business_data_service.base.BaseEntity;
import com.business_data_service.models.enums.MeasurementUnit;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Setter
@Getter
@Entity
@Table(name = "product")
public class ProductEntity extends BaseEntity {

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "product_code", nullable = false, unique = true)
    private String productCode;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @NotNull(message = "Weight unit cannot be null")
    @Enumerated(EnumType.STRING)
    @Column(name = "unit", nullable = false)
    private MeasurementUnit weightUnit;

    @Column(name = "gtin", unique = true)
    private String gtin;
}

