package com.project.Kdemy.service;

import com.razorpay.Order;
import com.razorpay.RazorpayException;

public interface RazorpayService {

    public Order createOrder(double amount) throws RazorpayException;

    public boolean verifySignature(
            String orderId, String paymentId, String signature);


}
