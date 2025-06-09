package com.bookstore.repository;

import com.bookstore.model.ForgetPassword;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForgetPasswordRepository extends JpaRepository<ForgetPassword,Long> {
    ForgetPassword findByEmail(String email);
}
