package com.github.rafasantos.twiliosmssample.account;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@ActiveProfiles("test")
@Disabled
class AccountServiceTest {
    @Autowired
    private AccountService target;

    @Test
    public void findAllPhoneNumbers_Then_ReturnPhoneNumbers() {
        var actual = target.findAllPhoneNumbers();
        assertFalse(actual.isEmpty(), "AccountService.findAllPhoneNumbers() returned no phoneNumbers");
    }
}