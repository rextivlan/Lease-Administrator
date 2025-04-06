package com.ali.LeaseAdmin.controller;

import com.ali.LeaseAdmin.model.Lease;
import com.ali.LeaseAdmin.service.LeaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/leases")
public class LeaseController {
    @Autowired
    private LeaseService leaseService;
    // Create a new lease contract.
    @PostMapping
    public ResponseEntity<Lease> createLease(@RequestBody Lease lease) {
        Lease createdLease = leaseService.createLease(lease);
        return ResponseEntity.ok(createdLease);
    }
}
