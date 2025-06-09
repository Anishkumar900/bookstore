package com.bookstore.request;

import com.bookstore.domain.USER_ROLE;
import lombok.Data;

@Data
public class ForgetPasswordRequest {
    private String email;
    private String otp;
    private USER_ROLE role;
    private String password;
}
