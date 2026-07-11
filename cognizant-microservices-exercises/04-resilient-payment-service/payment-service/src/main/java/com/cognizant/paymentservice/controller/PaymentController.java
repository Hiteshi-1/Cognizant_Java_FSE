package com.cognizant.paymentservice.controller;

import com.cognizant.paymentservice.dto.PaymentRequest;
import com.cognizant.paymentservice.dto.PaymentResponse;
import com.cognizant.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/charge")
    public ResponseEntity<PaymentResponse> charge(@RequestBody PaymentRequest request) throws InterruptedException {
        return ResponseEntity.ok(paymentService.processPayment(request));
    }
}
