package com.project.Kdemy.controller;

import com.project.Kdemy.service.PaymentService;
import com.razorpay.RazorpayException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
@PreAuthorize("hasRole('STUDENT')")
@AllArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/create/{courseId}")
    public ResponseEntity<?> createOrder(
            @PathVariable Long courseId,
            Authentication auth) throws RazorpayException {

        return ResponseEntity.ok(
                paymentService.createOrder(courseId, auth.getName())
        );
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verifyPayment(
            @RequestParam String orderId,
            @RequestParam String paymentId,
            @RequestParam String signature,
            Authentication auth) {

        paymentService.verifyPayment(
                orderId, paymentId, signature, auth.getName());

        return ResponseEntity.ok("Payment successful & enrolled");
    }
}
