package com.bookstore.response;

import com.bookstore.model.EmailVerificationRegistry;
import lombok.Data;

@Data
public class LoginResponse {
    private EmailVerificationRegistry emailVerificationRegistry;
    private String token;
    private boolean isSuccessful=false;
}
