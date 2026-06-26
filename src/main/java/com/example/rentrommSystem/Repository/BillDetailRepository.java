package com.example.rentrommSystem.Repository;

import com.example.rentrommSystem.Model.BillDetailModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillDetailRepository extends JpaRepository<BillDetailModel, Long> {
    List<BillDetailModel> findByBillBillId(Long billId);
}
