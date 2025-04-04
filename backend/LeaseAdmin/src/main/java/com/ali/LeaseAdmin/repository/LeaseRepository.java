package com.ali.LeaseAdmin.repository;

import com.ali.LeaseAdmin.model.Lease;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaseRepository extends JpaRepository<Lease, Long> {

}
