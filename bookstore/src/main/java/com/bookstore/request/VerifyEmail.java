package com.bookstore.request;

import com.bookstore.domain.USER_ROLE;
import lombok.Data;

@Data
public class VerifyEmail {
    private String email;
    private USER_ROLE role;
    private String otp;

}
