package com.github.rafasantos.twiliosmssample.ui;

import com.github.rafasantos.twiliosmssample.account.AccountService;
import com.github.rafasantos.twiliosmssample.sms.SmsService;
import com.twilio.rest.api.v2010.account.Message;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/messages")
@AllArgsConstructor
@Slf4j
public class MessagesController {
    private final AccountService accountService;
    private final SmsService smsService;

    @GetMapping
    public String get(@RequestParam("fromPhoneNumber") Optional<String> fromPhoneNumber, Model model) {
        try {
            if (fromPhoneNumber.isPresent()) {
                List<Message> messagesSent = smsService.messagesReceived(fromPhoneNumber.get());
                List<Message> messagesReceived = smsService.messagesSent(fromPhoneNumber.get());
                List<MessagePojo> messages = mergeMessages(messagesSent, messagesReceived);
                model.addAttribute("messages", messages);
            }
        } catch (Exception e) {
            log.error("Error when finding messages: {}", e.getMessage(), e);
            model.addAttribute("error", "Error when finding messages " + e.getMessage());
        }

        Set<String> phoneNumbers = accountService.findAllPhoneNumbers();
        model.addAttribute("phoneNumbers", phoneNumbers);
        return "messages-page";
    }

    private List<MessagePojo> mergeMessages(List<Message> messagesSent, List<Message> messagesReceived) {
        List<MessagePojo> messages = new ArrayList<>();
        messages.addAll(messagesSent.stream()
                .map(m -> {
                    var pojo = toPojo(m);
                    pojo.setDirection("inbound");
                    return pojo;
                })
                .collect(Collectors.toList()));
        messages.addAll(messagesReceived.stream()
                .map(m -> {
                    var pojo = toPojo(m);
                    pojo.setDirection("outbound");
                    return pojo;
                })
                .collect(Collectors.toList()));
        messages.sort((m1, m2) -> {
            if (m1.getDateSent().isBefore(m2.getDateSent())) {
                return 1;
            } else {
                return -1;
            }
        });
        return messages;
    }

    private static MessagePojo toPojo(Message message) {
        return MessagePojo.builder()
                .body(message.getBody())
                .dateSent(message.getDateSent())
                .from(message.getFrom().toString())
                .to(message.getTo())
                .sid(message.getSid())
                .status(message.getStatus().toString())
                .build();
    }
}
