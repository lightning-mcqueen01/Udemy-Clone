package com.project.Kdemy.service.ServiceImpl;

import com.project.Kdemy.service.RazorpayService;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Service
public class RazorpayServiceImpl implements RazorpayService {

    private RazorpayClient client;

    @Value("${razorpay.key-secret}")
    private String secret;

    public RazorpayServiceImpl(
            @Value("${razorpay.key-id}") String key,
            @Value("${razorpay.key-secret}") String secret)
            throws RazorpayException {

        this.client = new RazorpayClient(key, secret);
    }

    @Override
    public Order createOrder(double amount) throws RazorpayException {

        JSONObject options = new JSONObject();
        options.put("amount", amount * 100);
        options.put("currency", "INR");
        options.put("receipt", "txn_" + System.currentTimeMillis());

        return client.orders.create(options);
    }
    @Override
    public boolean verifySignature(
            String orderId, String paymentId, String signature) {

        try {
            String data = orderId + "|" + paymentId;
            String generated = hmacSha256(data, secret);
            return generated.equals(signature);
        } catch (Exception e) {
            return false;
        }
    }

    private String hmacSha256(String data, String secret) throws Exception {
        Mac mac = Mac.getInstance("HmacSHA256");
        SecretKeySpec keySpec =
                new SecretKeySpec(secret.getBytes(), "HmacSHA256");
        mac.init(keySpec);
        byte[] raw = mac.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(raw);
    }
}
