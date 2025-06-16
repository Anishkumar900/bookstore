package com.bookstore.repository;

import com.bookstore.model.VendorDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepository extends JpaRepository<VendorDetails,Long> {
    VendorDetails findByEmail(String email);
}
