package com.bookstore.service;

import com.bookstore.request.ForgetPasswordRequest;
import com.bookstore.request.LoginRequest;
import com.bookstore.request.RegisterUser;
import com.bookstore.request.VerifyEmail;
import com.bookstore.response.GeneralResponse;
import com.bookstore.response.LoginResponse;

public interface EmailVerificationRegistryService {
    boolean generateOtp(VerifyEmail verifyEmail);
    boolean emailVerification(VerifyEmail verifyEmail);
    String userRegistered(RegisterUser registerUser);
    LoginResponse login(LoginRequest loginRequest);
    GeneralResponse forgetPasswordGenerateOTP(ForgetPasswordRequest forgetPasswordRequest);
    GeneralResponse resetPassword(ForgetPasswordRequest forgetPasswordRequest);
}
