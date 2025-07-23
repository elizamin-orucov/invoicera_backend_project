package com.business_data_service.repositories;

import com.business_data_service.models.InvoiceFastEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceFastRepository extends JpaRepository<InvoiceFastEntity, Long> {
}
