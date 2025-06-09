package com.bookstore.response;

import lombok.Data;

@Data
public class GeneralResponse {
    private String email;
    private String token;
    private String message;
    private boolean isSuccessful=false;
}
