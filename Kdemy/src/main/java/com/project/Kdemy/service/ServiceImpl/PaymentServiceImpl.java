package com.project.Kdemy.service.ServiceImpl;

import com.project.Kdemy.exception.ResourceNotFoundException;
import com.project.Kdemy.model.Course;
import com.project.Kdemy.model.Payment;
import com.project.Kdemy.repository.CourseRepository;
import com.project.Kdemy.repository.PaymentRepository;
import com.project.Kdemy.service.EnrollmentService;
import com.project.Kdemy.service.PaymentService;
import com.project.Kdemy.service.RazorpayService;
import com.project.Kdemy.model.PaymentStatus;
import com.razorpay.Order;
import com.razorpay.RazorpayException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final RazorpayService razorpayService;
    private final PaymentRepository paymentRepo;
    private final EnrollmentService enrollmentService;
    private final CourseRepository courseRepo;

    @Override
    public Order createOrder(Long courseId, String email)
            throws RazorpayException {

        Course course = courseRepo.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        Order order = razorpayService.createOrder(course.getPrice());

        Payment payment = new Payment();
        payment.setRazorpayOrderId(order.get("id"));
        payment.setAmount(course.getPrice());
        payment.setStatus(PaymentStatus.PENDING);
        payment.setStudentEmail(email);
        payment.setCourse(course);

        paymentRepo.save(payment);
        return order;
    }

    @Override
    public void verifyPayment(
            String orderId,
            String paymentId,
            String signature,
            String email) {

        if (!razorpayService.verifySignature(orderId, paymentId, signature)) {
            throw new RuntimeException("Payment verification failed");
        }

        Payment payment = paymentRepo.findByRazorpayOrderId(orderId).orElseThrow(() -> new ResourceNotFoundException("payment not found"));

        payment.setRazorpayPaymentId(paymentId);
        payment.setRazorpaySignature(signature);
        payment.setStatus(PaymentStatus.SUCCESS);

        paymentRepo.save(payment);

        enrollmentService.enroll(payment.getCourse().getId(), email);
    }
}
