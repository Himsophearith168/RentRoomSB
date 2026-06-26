package com.example.rentrommSystem.Model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PaymentModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long paymentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bill_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private BillModel bill;

    @Column(name = "amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method", nullable = false)
    @Builder.Default
    private PaymentMethod paymentMethod = PaymentMethod.Cash;

    @Column(name = "payment_date")
    private LocalDateTime paymentDate;

    @Column(name = "proof_image")
    private String proofImage;

    @Column(name = "remarks", columnDefinition = "TEXT")
    private String remarks;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now();
        if (paymentMethod == null) {
            paymentMethod = PaymentMethod.Cash;
        }
        if (paymentDate == null) {
            paymentDate = LocalDateTime.now();
        }
    }
}
