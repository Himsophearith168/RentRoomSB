package com.example.rentrommSystem.Model;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "utility_rates")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class utilityRatesModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rate_id")
    private Integer rateId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "utility_type_id", nullable = false)
    private utilityTypesModel utilityType;

    @Column(name = "unit_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal unitPrice;

    @Column(name = "effective_from", nullable = false)
    private LocalDate effectiveFrom;

    @Column(name = "is_active")
    @Builder.Default
    private Boolean isActive = true;

}

