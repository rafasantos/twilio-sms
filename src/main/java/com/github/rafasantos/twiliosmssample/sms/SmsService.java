package com.github.rafasantos.twiliosmssample.sms;

import com.twilio.http.TwilioRestClient;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
@AllArgsConstructor
public class SmsService {
    private final TwilioRestClient twilioRestClient;

    public Message sendSms(String to, String from, String text) {
        PhoneNumber toPhoneNumber = new PhoneNumber(to);
        PhoneNumber fromPhoneNumber = new PhoneNumber(from);
        Message message = Message.creator(
                toPhoneNumber,
                fromPhoneNumber,
                text)
                .create(twilioRestClient);
        log.info("SMS sent. MessageSid: {}; MessageStatus: {}", message.getSid(), message.getStatus());
        return message;
    }

    public List<Message> messagesSent(String fromPhoneNumber) {
        var messages = Message.reader()
                .setFrom(fromPhoneNumber)
                .limit(100)
                .read(twilioRestClient);
        List<Message> result = new ArrayList<>();
        for (var message: messages) {
            result.add(message);
        }
        return result;
    }

    public List<Message> messagesReceived(String fromPhoneNumber) {
        var messages = Message.reader()
                .setTo(fromPhoneNumber)
                .limit(100)
                .read(twilioRestClient);
        List<Message> result = new ArrayList<>();
        for (var message: messages) {
            result.add(message);
        }
        return result;
    }
}
