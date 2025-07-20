package com.business_data_service.repositories;

import com.business_data_service.models.InvoiceAutomaticEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface InvoiceAutomaticRepository extends JpaRepository<InvoiceAutomaticEntity, Long> {
    List<InvoiceAutomaticEntity> findByDate(LocalDate date);
}

