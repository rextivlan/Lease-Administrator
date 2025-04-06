package com.ali.LeaseAdmin.service;

import com.ali.LeaseAdmin.model.Lease;
import com.ali.LeaseAdmin.repository.LeaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LeaseService {

    @Autowired
    private LeaseRepository leaseRepository;

    // Create a new Lease contract with basic business logic for lease accounting.
    public Lease createLease(Lease lease) {
        // Example business logic: Calculate Right-of-Use Asset and Lease Liability.
        if (lease.getLeasePayment() != null) {
            lease.setRightOfUseAsset(lease.getLeasePayment() * 12); // Simplified calculation.
            lease.setLeaseLiability(lease.getLeasePayment() * 12);
        }
        return leaseRepository.save(lease);
    }

    // Retrieve all lease contracts.
    public List<Lease> getAllLeases() {
        return leaseRepository.findAll();
    }

    // Retrieve a specific lease by its ID.
    public Optional<Lease> getLeaseById(Long id) {
        return leaseRepository.findById(id);
    }

    // Update an existing lease contract.
    public Lease updateLease(Long id, Lease updatedLease) {
        return leaseRepository.findById(id).map(existingLease -> {
            existingLease.setContractNumber(updatedLease.getContractNumber());
            existingLease.setLeaseStartDate(updatedLease.getLeaseStartDate());
            existingLease.setLeaseEndDate(updatedLease.getLeaseEndDate());
            existingLease.setLeaseType(updatedLease.getLeaseType());
            existingLease.setLessee(updatedLease.getLessee());
            existingLease.setLessor(updatedLease.getLessor());
            existingLease.setLeasePayment(updatedLease.getLeasePayment());
            // Recalculate the accounting values when the payment changes.
            if (updatedLease.getLeasePayment() != null) {
                existingLease.setRightOfUseAsset(updatedLease.getLeasePayment() * 12);
                existingLease.setLeaseLiability(updatedLease.getLeasePayment() * 12);
            }
            existingLease.setResidualValue(updatedLease.getResidualValue());
            existingLease.setInitialDirectCosts(updatedLease.getInitialDirectCosts());
            return leaseRepository.save(existingLease);
        }).orElseThrow(() -> new RuntimeException("Lease not found with id " + id));
    }

    // Delete a lease contract by its ID.
    public void deleteLease(Long id) {
        leaseRepository.deleteById(id);
    }
}
