package com.ali.LeaseAdmin.controller;

import com.ali.LeaseAdmin.model.Lease;
import com.ali.LeaseAdmin.service.LeaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    // Retrieve all lease contracts.
    @GetMapping
    public ResponseEntity<List<Lease>> getAllLeases() {
        return ResponseEntity.ok(leaseService.getAllLeases());
    }

    // Retrieve a lease contract by its ID.
    @GetMapping("/{id}")
    public ResponseEntity<Lease> getLeaseById(@PathVariable Long id) {
        return leaseService.getLeaseById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update an existing lease contract.
    @PutMapping("/{id}")
    public ResponseEntity<Lease> updateLease(@PathVariable Long id, @RequestBody Lease lease) {
        Lease updatedLease = leaseService.updateLease(id, lease);
        return ResponseEntity.ok(updatedLease);
    }

    // Delete a lease contract.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLease(@PathVariable Long id) {
        leaseService.deleteLease(id);
        return ResponseEntity.noContent().build();
    }
}
