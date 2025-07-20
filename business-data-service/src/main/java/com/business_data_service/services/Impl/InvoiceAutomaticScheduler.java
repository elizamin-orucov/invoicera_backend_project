package com.business_data_service.services.Impl;

import com.business_data_service.models.InvoiceAutomaticEntity;
import com.business_data_service.repositories.InvoiceAutomaticRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InvoiceAutomaticScheduler {

    private final InvoiceAutomaticRepository repository;

    @Scheduled(cron = "0 0 0 * * *")
//    @Scheduled(cron = "*/10 * * * * *")
    public void processInvoices() {
        System.out.println("yoxlama 1");
        LocalDate today = LocalDate.now();
        List<InvoiceAutomaticEntity> invoices = repository.findByDate(today);

        for (InvoiceAutomaticEntity invoice : invoices) {
            System.out.println("Auto request musteri: " + invoice.getCustomer() + ", Product: " + invoice.getProduct());
        }
    }
}

