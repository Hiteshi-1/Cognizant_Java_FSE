package com.cognizant.paymentservice.service;

import com.cognizant.paymentservice.client.ThirdPartyPaymentClient;
import com.cognizant.paymentservice.dto.PaymentRequest;
import com.cognizant.paymentservice.dto.PaymentResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private static final Logger log = LoggerFactory.getLogger(PaymentService.class);

    private final ThirdPartyPaymentClient thirdPartyPaymentClient;

    // "paymentService" below must match resilience4j.circuitbreaker.instances.paymentService in application.yml
    @CircuitBreaker(name = "paymentService", fallbackMethod = "fallbackCharge")
    public PaymentResponse processPayment(PaymentRequest request) throws InterruptedException {
        log.info("Attempting to charge order {} via third-party gateway", request.getOrderId());
        return thirdPartyPaymentClient.charge(request);
    }

    // Fallback signature must match the original method + a Throwable parameter
    public PaymentResponse fallbackCharge(PaymentRequest request, Throwable throwable) {
        // Log and monitor fallback events, as required by the exercise
        log.warn("FALLBACK triggered for order {} - reason: {}", request.getOrderId(), throwable.toString());
        return new PaymentResponse(
                request.getOrderId(),
                "FALLBACK",
                "Payment gateway is currently unavailable. Your order has been queued for retry."
        );
    }
}
