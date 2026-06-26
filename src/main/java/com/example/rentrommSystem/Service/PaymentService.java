package com.example.rentrommSystem.Service;

import com.example.rentrommSystem.DTO.PaymentRequest;
import com.example.rentrommSystem.DTO.PaymentResponse;
import com.example.rentrommSystem.Mapper.PaymentMapper;
import com.example.rentrommSystem.Model.BillModel;
import com.example.rentrommSystem.Model.BillStatus;
import com.example.rentrommSystem.Model.PaymentModel;
import com.example.rentrommSystem.Repository.BillRepository;
import com.example.rentrommSystem.Repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final BillRepository billRepository;
    private final PaymentMapper paymentMapper;

    public List<PaymentResponse> findAll() {
        return paymentRepository.findAll().stream()
                .map(paymentMapper::toResponse)
                .collect(Collectors.toList());
    }

    public PaymentResponse findById(Long id) {
        PaymentModel payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found with id: " + id));
        return paymentMapper.toResponse(payment);
    }

    public List<PaymentResponse> findByBill(Long billId) {
        return paymentRepository.findByBillBillId(billId).stream()
                .map(paymentMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public PaymentResponse createPayment(PaymentRequest request) {
        if (request.getAmount() == null || request.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Payment amount must be greater than zero");
        }

        BillModel bill = billRepository.findById(request.getBillId())
                .orElseThrow(() -> new RuntimeException("Bill not found with id: " + request.getBillId()));

        PaymentModel payment = paymentMapper.toEntity(request);
        payment.setBill(bill);

        PaymentModel savedPayment = paymentRepository.save(payment);
        updateBillStatus(bill);

        return paymentMapper.toResponse(savedPayment);
    }

    @Transactional
    public void deletePayment(Long id) {
        PaymentModel payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found with id: " + id));
        BillModel bill = payment.getBill();
        
        paymentRepository.delete(payment);

        updateBillStatus(bill);
    }

    private void updateBillStatus(BillModel bill) {
        List<PaymentModel> payments = paymentRepository.findByBillBillId(bill.getBillId());
        BigDecimal totalPaid = payments.stream()
                .map(PaymentModel::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        if (totalPaid.compareTo(BigDecimal.ZERO) == 0) {
            if (bill.getStatus() != BillStatus.Draft) {
                bill.setStatus(BillStatus.Unpaid);
            }
        } else if (totalPaid.compareTo(bill.getTotalAmount()) >= 0) {
            bill.setStatus(BillStatus.Paid);
        } else {
            bill.setStatus(BillStatus.Partially_Paid);
        }
        billRepository.save(bill);
    }
}
