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

import static com.dev.service.impl.otp.SmsPayloadBuilder.buildSmsPayload;

@Service
public class SmsService {

    private static final Logger logger = LoggerFactory.getLogger(SmsService.class);

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public SmsService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public boolean sendOtp(String phoneNumber, String otpCode) {
        String url = "https://apps.lsim.az/quicksms/v1/smssender";

        Map<String, String> payload = buildSmsPayload(phoneNumber, otpCode);

        System.out.println("+++++++++++++++++++++++=");
        System.out.println("+++++++++++++++++++++++=");
        System.out.println("+++++++++++++++++++++++=");
        System.out.println(phoneNumber);
        System.out.println("+++++++++++++++++++++++=");
        System.out.println("+++++++++++++++++++++++=");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(payload, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                JsonNode json = objectMapper.readTree(response.getBody());

                String transactionId = json.has("obj") ? json.get("obj").asText() : null;

                System.out.println("---------------------------");
                System.out.println("---------------------------");
                System.out.println(json);
                System.out.println("---------------------------");
                System.out.println("---------------------------");

                if (transactionId != null) {
                    logger.info("OTP gönderildi, transactionId: {}", transactionId);

                    // SMSModel kaydetme işlemi burada olabilir
                    // SMSModel sms = new SMSModel();
                    // sms.setTransactionId(transactionId);
                    // sms.save();

                    return true;
                } else {
                    logger.error("Response içinde 'obj' alanı yok.");
                    return false;
                }
            } else {
                logger.error("OTP gönderme başarısız oldu, status: {}", response.getStatusCode());
                return false;
            }

        } catch (Exception e) {
            logger.error("OTP gönderilirken hata oluştu: ", e);
            return false;
        }
    }
}
