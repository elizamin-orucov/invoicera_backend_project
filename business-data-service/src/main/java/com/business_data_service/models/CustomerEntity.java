package com.business_data_service.models;

import com.business_data_service.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "customer")
public class CustomerEntity extends BaseEntity {
    @Column(nullable = false, name = "customer_name")
    private String customerName;

    @Column(nullable = false, name = "customer_surname")
    private String customerSurname;

    @Column(nullable = false, name = "contact")
    private String contact;

    @Column(nullable = false, name = "position")
    private String position;

    @Column(nullable = false, name = "institution")
    private String institution;

    @Column(nullable = false, name = "TIN")
    private String TIN;
}
