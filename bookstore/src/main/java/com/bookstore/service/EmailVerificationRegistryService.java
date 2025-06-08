package com.bookstore.service;

import com.bookstore.request.VerifyEmail;

public interface EmailVerificationRegistryService {
    boolean generateOtp(VerifyEmail verifyEmail);
}
