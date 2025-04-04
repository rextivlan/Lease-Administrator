package com.ali.LeaseAdmin.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Lease {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Unique contract number for the lease
    private String contractNumber;

    // Lease period start and end dates
    private LocalDate leaseStartDate;
    private LocalDate leaseEndDate;

    // Lease type (e.g., Operating or Finance)
    private String leaseType;

    // Parties involved in the lease
    private String lessee;
    private String lessor;

    // Periodic lease payment amount
    private Double leasePayment;

    // Lease accounting terms:
    // - Right-of-Use Asset (ROU Asset) as per IFRS 16/ASC 842
    // - Lease Liability (obligation to make future payments)
    private Double rightOfUseAsset;
    private Double leaseLiability;

    // Additional fields for comprehensive accounting
    private Double residualValue;
    private Double initialDirectCosts;
}
