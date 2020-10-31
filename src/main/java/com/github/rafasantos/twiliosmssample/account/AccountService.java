package com.github.rafasantos.twiliosmssample.account;

import com.twilio.http.TwilioRestClient;
import com.twilio.rest.api.v2010.Account;
import com.twilio.rest.api.v2010.account.IncomingPhoneNumber;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@AllArgsConstructor
public class AccountService {
    private final TwilioRestClient twilioRestClient;

    public Set<String> findAllPhoneNumbers() {
        final Set<String> result = new HashSet<>();
        var account = Account.fetcher().fetch(twilioRestClient);
        var incomingPhoneNumbers = IncomingPhoneNumber.reader(account.getSid()).read(twilioRestClient);
        for (var incomePhoneNumber : incomingPhoneNumbers) {
            result.add(incomePhoneNumber.getPhoneNumber().getEndpoint());
        }
        return result;
    }
}
