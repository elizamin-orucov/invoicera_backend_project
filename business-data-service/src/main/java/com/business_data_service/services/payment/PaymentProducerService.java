package com.business_data_service.services.payment;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PaymentProducerService {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public PaymentProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendPaymentEvent(Long invoiceId) {
        kafkaTemplate.send("payment-topic", invoiceId.toString());
    }
}

