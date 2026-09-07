package com.mycompany.Softwarepr1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.mycompany.Softwarepr1.models.*;

public class PaymentTest {

    @Test
    public void testPaymentSettersAndGetters() {
        Payment payment = new Payment();

        payment.setCardHolderName("John Doe");
        payment.setCardNumber("1234123412341234");
        payment.setCvv("123");
        payment.setPostalCode("12345");
        payment.setExpiryMonth("12");
        payment.setExpiryYear("2026");

        assertEquals("John Doe", payment.getCardHolderName());
        assertEquals("1234123412341234", payment.getCardNumber());
        assertEquals("123", payment.getCvv());
        assertEquals("12345", payment.getPostalCode());
        assertEquals("12", payment.getExpiryMonth());
        assertEquals("2026", payment.getExpiryYear());
    }
}
