package com.github.rafasantos.twiliosmssample;

import com.github.rafasantos.twiliosmssample.account.AccountService;
import com.github.rafasantos.twiliosmssample.sms.SmsService;
import com.twilio.rest.api.v2010.account.Message;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Disabled
public class EndToEndTest {
    private final int FIVE_SECONDS_IN_MILLIS = 5 * 1000;
    @Value("${test.endtoend.from_phone_number}")
    private String fromPhoneNumber;
    @Value("${test.endtoend.to_phone_number}")
    private String toPhoneNumber;

    @Autowired
    private AccountService accountService;
    @Autowired
    private SmsService smsService;

    @Test
    void endToEndTest() throws InterruptedException {
        var phoneNumbers = accountService.findAllPhoneNumbers();
        assertFalse(phoneNumbers.isEmpty(), "AccountService.findAllPhoneNumbers() returned no phoneNumbers");
        assertTrue(phoneNumbers.contains(fromPhoneNumber), "AccountService.findAllPhoneNumbers() does not contain " + fromPhoneNumber);

        var messageSent = smsService.sendSms(toPhoneNumber, fromPhoneNumber, "End to end test message");
        assertNotNull(messageSent, "SmsService.sendSms() returned null message");
        assertNotNull(messageSent.getSid(), "messageSent has null sid");
        assertEquals(fromPhoneNumber, messageSent.getFrom().getEndpoint(), "messageSent from does not match fromPhoneNumber");
        assertEquals(toPhoneNumber, messageSent.getTo(), "messageSent to does not match toPhoneNumber");

        // Wait 5 seconds because Twilio it takes a few seconds before it is available on Twilio Message Resource
        Thread.sleep(FIVE_SECONDS_IN_MILLIS);
        List<Message> messagesSent = smsService.messagesSent(fromPhoneNumber);
        assertFalse(messagesSent.isEmpty(), "SmsService.messagesSet() is empty");
        boolean messageWasSet = messagesSent.stream().anyMatch(p -> p.getSid().equals(messageSent.getSid()));
        assertTrue(messageWasSet, "SmsService.messageSet() does not contain the sms just sent");
    }
}
