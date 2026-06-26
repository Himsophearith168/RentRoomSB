package com.example.rentrommSystem.Model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bills")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Setter
@Getter
public class BillModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long billId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignment_id", nullable = false)
    private RoomAssignModel roomAssignment;

    @Column(nullable = false)
    private LocalDate billMonth;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal roomRent;

    @Builder.Default
    @Column(precision = 10, scale = 2)
    private BigDecimal otherFee = BigDecimal.ZERO;

    @Builder.Default
    @Column(precision = 10, scale = 2)
    private BigDecimal totalAmount = BigDecimal.ZERO;

    @Convert(converter = BillStatusConverter.class)
    @Column(nullable = false)
    private BillStatus status;

    private LocalDate dueDate;

    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "bill", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Builder.Default
    private List<BillDetailModel> billDetails = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();

        if (status == null) {
            status = BillStatus.Draft;
        }

        if (otherFee == null) {
            otherFee = BigDecimal.ZERO;
        }

        if (totalAmount == null) {
            totalAmount = BigDecimal.ZERO;
        }
    }
}
