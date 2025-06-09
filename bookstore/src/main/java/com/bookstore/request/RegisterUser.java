package com.bookstore.request;

import com.bookstore.domain.USER_ROLE;
import lombok.Data;

@Data
public class RegisterUser {
    private String email;
    private USER_ROLE role;
    private String fullName;
    private String phoneNumber;
    private String land;
    private String city;
    private Long pin;
    private String state;
    private String password;
}
