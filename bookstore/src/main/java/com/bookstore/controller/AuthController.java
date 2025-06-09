package com.bookstore.controller;

import com.bookstore.request.ForgetPasswordRequest;
import com.bookstore.request.LoginRequest;
import com.bookstore.request.RegisterUser;
import com.bookstore.request.VerifyEmail;
import com.bookstore.response.GeneralResponse;
import com.bookstore.response.LoginResponse;
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

//        System.out.println(verifyEmail);
        if (emailVerificationRegistryService.generateOtp(verifyEmail)) {
            return new ResponseEntity<>("Generate OTP SUCCESSFUL", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User already registered", HttpStatus.BAD_REQUEST);
        }
//        return new ResponseEntity<>("Generate OTP SUCCESSFUL", HttpStatus.OK);
    }
    @PostMapping("/verification")
    public ResponseEntity<?> emailVerification(@RequestBody VerifyEmail verifyEmail){
        if(emailVerificationRegistryService.emailVerification(verifyEmail)){
            return new ResponseEntity<>("Email verified successful.",HttpStatus.OK);
        }
        return new ResponseEntity<>("Please enter correct OTP.", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/registry")
    public ResponseEntity<?> registerUser(@RequestBody RegisterUser registerUser) {
        String result = emailVerificationRegistryService.userRegistered(registerUser);

        if (result.equals("User not found or not pre-verified.")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }

        if (result.equals("Email already Registered. Registration not allowed.")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }
        if(result.equals("Email not verified. Registration not allowed. Please verify your email.")){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }

        // Registration successful — return token
        return ResponseEntity.ok(result);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        LoginResponse loginResponse = emailVerificationRegistryService.login(loginRequest);

        if (loginResponse.isSuccessful()) {
            return ResponseEntity.ok(loginResponse);  // 200 OK with login data
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials or role.");
        }
    }

    @PostMapping("/forget-password/generate-otp")
    public ResponseEntity<?> passwordGenerateOTP(@RequestBody ForgetPasswordRequest forgetPasswordRequest){
        GeneralResponse generalResponse=emailVerificationRegistryService.forgetPasswordGenerateOTP(forgetPasswordRequest);
        if(generalResponse.isSuccessful()){
            return ResponseEntity.ok(generalResponse);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(generalResponse);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> setPassword(@RequestBody ForgetPasswordRequest forgetPasswordRequest){
        GeneralResponse generalResponse=emailVerificationRegistryService.resetPassword(forgetPasswordRequest);
        if(generalResponse.isSuccessful()){
            return ResponseEntity.ok(generalResponse);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(generalResponse);
    }


}
