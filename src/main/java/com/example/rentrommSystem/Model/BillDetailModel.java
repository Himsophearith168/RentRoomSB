package com.example.rentrommSystem.Model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;

@Entity
@Table(name = "bill_details")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BillDetailModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "detail_id")
    private Long detailId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bill_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private BillModel bill;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "utility_type_id", nullable = false)
    private utilityTypesModel utilityType;

    @Column(name = "old_reading", precision = 10, scale = 2)
    private BigDecimal oldReading;

    @Column(name = "new_reading", precision = 10, scale = 2)
    private BigDecimal newReading;

    @Column(name = "quantity", nullable = false, precision = 10, scale = 2)
    private BigDecimal quantity;

    @Column(name = "unit_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal unitPrice;

    @Column(name = "total_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalPrice;

    @Column(name = "notes")
    private String notes;
}
