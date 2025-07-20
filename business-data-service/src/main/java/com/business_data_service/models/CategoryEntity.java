package com.business_data_service.models;

import com.business_data_service.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "category")
public class CategoryEntity extends BaseEntity {

    @Column(nullable = false, name = "category_name")
    private String categoryName;

    @Column(nullable = false, name = "category_code", unique = true, updatable = false)
    private String categoryCode;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private CategoryEntity parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<CategoryEntity> children;
}

