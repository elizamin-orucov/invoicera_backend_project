package com.dev.service.impl.otp;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;


@Service
public class SmsService {

    private static final Logger logger = LoggerFactory.getLogger(SmsService.class);

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final SmsPayloadBuilder smsPayloadBuilder;

    public SmsService(RestTemplate restTemplate, ObjectMapper objectMapper, SmsPayloadBuilder smsPayloadBuilder) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
        this.smsPayloadBuilder = smsPayloadBuilder;
    }

    public boolean sendOtp(String phoneNumber, String otpCode) {
        String url = "https://apps.lsim.az/quicksms/v1/smssender";

        Map<String, String> payload = smsPayloadBuilder.buildSmsPayload(phoneNumber, otpCode);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(payload, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
            System.out.println("-------------------------------");
            System.out.println("-------------------------------");
            System.out.println("-------------------------------");
            System.out.println("-------------------------------");
            System.out.println("-------------------------------");
            System.out.println("-------------------------------");
            System.out.println("-------------------------------");
            System.out.println("-------------------------------");
            System.out.println(response);
            System.out.println("-------------------------------");
            System.out.println("-------------------------------");
            System.out.println("-------------------------------");
            System.out.println("-------------------------------");
            System.out.println("-------------------------------");
            System.out.println("-------------------------------");
            System.out.println("-------------------------------");
            System.out.println("-------------------------------");
            System.out.println("-------------------------------");
            System.out.println("-------------------------------");
            System.out.println("-------------------------------");
            System.out.println("-------------------------------");

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                JsonNode json = objectMapper.readTree(response.getBody());

                String transactionId = json.has("obj") ? json.get("obj").asText() : null;


                if (transactionId != null) {
                    logger.info("OTP success, transactionId: {}", transactionId);


                    // SMSModel sms = new SMSModel();
                    // sms.setTransactionId(transactionId);
                    // sms.save();

                    return true;
                } else {
                    logger.error("obj not found");
                    return false;
                }
            } else {
                logger.error("OTP failed: {}", response.getStatusCode());
                return false;
            }

        } catch (Exception e) {
            logger.error("OTP send error: ", e);
            return false;
        }
    }
}
