package com.business_data_service.repositories;

import com.business_data_service.models.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    List<CategoryEntity> findByParentId(Long parentId);

    boolean existsByCategoryCode(String categoryCode);
}
