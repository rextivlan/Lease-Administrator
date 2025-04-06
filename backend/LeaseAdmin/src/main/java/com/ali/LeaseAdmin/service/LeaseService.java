package com.ali.LeaseAdmin.service;

import com.ali.LeaseAdmin.model.Lease;
import com.ali.LeaseAdmin.repository.LeaseRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class LeaseService {

    @Autowired
    private LeaseRepository leaseRepository;
    public Lease createLease(Lease lease) {
        // Create a new Lease contract
        return leaseRepository.save(lease);
    }
}
