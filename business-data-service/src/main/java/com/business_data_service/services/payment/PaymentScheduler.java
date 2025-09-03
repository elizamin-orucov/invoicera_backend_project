package com.business_data_service.services.payment;

import com.business_data_service.models.InvoiceAutomaticEntity;
import com.business_data_service.repositories.InvoiceAutomaticRepository;
import com.business_data_service.repositories.InvoiceRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PaymentScheduler {

    private final InvoiceAutomaticRepository invoiceRepository;
    private final PaymentProducerService paymentProducer;

    public PaymentScheduler(InvoiceAutomaticRepository invoiceRepository, PaymentProducerService paymentProducer) {
        this.invoiceRepository = invoiceRepository;
        this.paymentProducer = paymentProducer;
    }

    @Scheduled(cron = "0 00 00 * * *")
    public void checkAndSendToKafka() {
        LocalDate today = LocalDate.now();
        List<InvoiceAutomaticEntity> invoices = invoiceRepository.findByDate(today);
        for (InvoiceAutomaticEntity invoice : invoices) {
            paymentProducer.sendPaymentEvent(invoice.getId());
            System.out.println("Kafka-ya göndərildi: " + invoice.getId());
        }
    }
}

