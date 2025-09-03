package com.business_data_service.services.payment;

import com.business_data_service.models.InvoiceAutomaticEntity;
import com.business_data_service.repositories.InvoiceAutomaticRepository;
import com.business_data_service.repositories.InvoiceRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class PaymentConsumerService {

    private final InvoiceAutomaticRepository invoiceRepository;

    public PaymentConsumerService(InvoiceAutomaticRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @KafkaListener(topics = "payment-topic", groupId = "payment-group")
    public void processPayment(String invoiceIdStr) {
        Long invoiceId = Long.valueOf(invoiceIdStr);
        InvoiceAutomaticEntity invoice = invoiceRepository.findById(invoiceId).orElseThrow();

        // Ödəniş prosesi (burada API çağırışı da ola bilər)
//        invoice.setStatus("PAID");
        System.out.println("calisdi");
        System.out.println("calisdi");
        System.out.println("calisdi");
        System.out.println("calisdi");
        System.out.println("calisdi");
        System.out.println("calisdi");
        System.out.println("calisdi");
        System.out.println("calisdi");
        invoiceRepository.save(invoice);

        System.out.println("Kafka ilə ödəniş işləndi: " + invoiceId);
    }
}

