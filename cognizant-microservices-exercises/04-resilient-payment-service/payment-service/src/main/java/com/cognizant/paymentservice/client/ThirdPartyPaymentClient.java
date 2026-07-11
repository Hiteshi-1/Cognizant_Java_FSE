package com.cognizant.paymentservice.client;

import com.cognizant.paymentservice.dto.PaymentRequest;
import com.cognizant.paymentservice.dto.PaymentResponse;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Stand-in for a real third-party payment gateway. Deliberately slow (and
 * occasionally throws) to demonstrate the circuit breaker + fallback behavior.
 * Replace the body of charge() with an actual WebClient/RestClient call to the
 * real provider (Stripe, Razorpay, etc.) when wiring this up for real use.
 */
@Component
public class ThirdPartyPaymentClient {

    public PaymentResponse charge(PaymentRequest request) throws InterruptedException {
        // Simulate network latency of a slow upstream provider (2-4s)
        Thread.sleep(ThreadLocalRandom.current().nextInt(2000, 4000));

        // Simulate ~40% failure rate from the upstream provider
        if (ThreadLocalRandom.current().nextInt(100) < 40) {
            throw new RuntimeException("Third-party payment gateway timeout/error");
        }

        return new PaymentResponse(request.getOrderId(), "SUCCESS", "Payment processed by third-party gateway");
    }
}
