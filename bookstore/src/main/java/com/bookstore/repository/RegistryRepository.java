package com.bookstore.repository;

import com.bookstore.model.EmailVerificationRegistry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistryRepository extends JpaRepository<EmailVerificationRegistry,Long> {
    EmailVerificationRegistry findByEmail(String email);
}
