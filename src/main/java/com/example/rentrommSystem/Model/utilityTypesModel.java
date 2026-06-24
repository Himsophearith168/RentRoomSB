package com.example.rentrommSystem.Model;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "utilityTypes")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class utilityTypesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long utilityTypeId;

    @Column(nullable = false, unique = true)
    private String utilityTypeName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BillingTypeStatus billingTypeStatus;

    @Column(nullable = false)
    private Long unit;

    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    @PrePersist
    public void prePersist() {
        createdDate = LocalDateTime.now();
        updatedDate = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedDate = LocalDateTime.now();
    }
}
