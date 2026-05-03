package com.project.Kdemy.service;

import com.razorpay.Order;
import com.razorpay.RazorpayException;

public interface PaymentService {

    public Order createOrder(Long courseId, String email)
            throws RazorpayException;

    public void verifyPayment(
            String orderId,
            String paymentId,
            String signature,
            String email);
}
