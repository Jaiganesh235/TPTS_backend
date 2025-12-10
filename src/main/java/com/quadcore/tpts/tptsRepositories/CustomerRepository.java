package com.quadcore.tpts.tptsRepository;

import com.quadcore.tpts.tptsModels.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    // Find customer by email
    Optional<Customer> findByEmail(String email);

    // Check if email exists
    boolean existsByEmail(String email);

    // Find by phone number
    Optional<Customer> findByPhoneNumber(String phoneNumber);

    // Find by full name (case-insensitive, with pagination)
    Page<Customer> findByFullNameContainingIgnoreCase(String fullName, Pageable pageable);

    // Find by city (with pagination)
    Page<Customer> findByCityIgnoreCase(String city, Pageable pageable);

    // Find active customers
    Page<Customer> findByIsActive(Boolean isActive, Pageable pageable);

    // Find by email and active status
    Optional<Customer> findByEmailAndIsActive(String email, Boolean isActive);
}
