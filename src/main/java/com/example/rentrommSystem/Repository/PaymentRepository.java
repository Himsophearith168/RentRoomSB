package com.example.rentrommSystem.Repository;

import com.example.rentrommSystem.Model.PaymentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentModel, Long> {
    List<PaymentModel> findByBillBillId(Long billId);
}
