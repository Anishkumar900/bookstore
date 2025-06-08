package com.bookstore.controller;

import com.bookstore.request.VerifyEmail;
import com.bookstore.service.EmailVerificationRegistryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final EmailVerificationRegistryService emailVerificationRegistryService;

    @PostMapping
    public ResponseEntity<String> generateOTP(@RequestBody VerifyEmail verifyEmail) {

        System.out.println(verifyEmail);
        if (emailVerificationRegistryService.generateOtp(verifyEmail)) {
            return new ResponseEntity<>("Generate OTP SUCCESSFUL", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User already registered", HttpStatus.BAD_REQUEST);
        }
//        return new ResponseEntity<>("Generate OTP SUCCESSFUL", HttpStatus.OK);
    }


}
