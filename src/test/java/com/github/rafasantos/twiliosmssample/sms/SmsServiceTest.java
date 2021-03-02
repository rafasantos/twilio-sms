package com.github.rafasantos.twiliosmssample.sms;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
@Disabled
class SmsServiceTest {
    @Value("${test.endtoend.from_phone_number}")
    private String fromPhoneNumber;

    @Autowired
    private SmsService smsService;

    @Test
    void messagesSent() {
        var actual = smsService.messagesSent(fromPhoneNumber);
        assertNotNull(actual);
        assertFalse(actual.isEmpty());
    }

    @Test
    void messagesReceived() {
        var actual = smsService.messagesReceived(fromPhoneNumber);
        assertNotNull(actual);
        assertFalse(actual.isEmpty());
    }
}