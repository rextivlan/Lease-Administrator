package com.ali.LeaseAdmin.repository;

import com.ali.LeaseAdmin.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<AppUser, Long> {
}
