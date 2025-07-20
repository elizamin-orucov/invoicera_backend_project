package com.business_data_service.models.backups;

import com.business_data_service.base.BaseEntity;
import com.business_data_service.models.InvoiceEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
//@Entity
//@Table(name = "category")
public class CategoryEntityBackup extends BaseEntity {

    @Column(nullable = false, name = "category_name")
    private String categoryName;

    @Column(nullable = false, name = "category_code", unique = true, updatable = false)
    private String categoryCode;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private CategoryEntityBackup parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<CategoryEntityBackup> children;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InvoiceEntity> invoices = new ArrayList<>();
}

