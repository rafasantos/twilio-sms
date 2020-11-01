package com.github.rafasantos.twiliosmssample.ui;

import com.github.rafasantos.twiliosmssample.account.AccountService;
import com.github.rafasantos.twiliosmssample.sms.SmsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

@Controller
@RequestMapping("/compose")
@AllArgsConstructor
public class ComposeController {
    private final AccountService accountService;
    private final SmsService smsService;

    @PostMapping
    public String post(
            @RequestParam("fromPhoneNumber") String fromPhoneNumber,
            @RequestParam("toPhoneNumber") String toPhoneNumber,
            @RequestParam("smsMessage") String smsMessage,
            HttpServletRequest request,
            Model model) {

//        smsService.sendSms()



        Set<String> phoneNumbers = accountService.findAllPhoneNumbers();
        model.addAttribute("phoneNumbers", phoneNumbers);
        return "compose-page";
    }

    @GetMapping
    public String get(HttpServletRequest request, Model model) {
        Set<String> phoneNumbers = accountService.findAllPhoneNumbers();
        model.addAttribute("phoneNumbers", phoneNumbers);
        return "compose-page";
    }
}
