package com.ali.LeaseAdmin.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")  // "user" might be a reserved keyword in some databases
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password; // stored as encoded password

    // Roles stored as a comma-separated string, e.g., "ROLE_USER,ROLE_ADMIN"
    private String roles;
}
