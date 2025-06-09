package com.bookstore.serviceImplemente;

import com.bookstore.config.JwtService;
import com.bookstore.model.Address;
import com.bookstore.model.EmailVerificationRegistry;
import com.bookstore.model.ForgetPassword;
import com.bookstore.repository.ForgetPasswordRepository;
import com.bookstore.repository.RegistryRepository;
import com.bookstore.request.ForgetPasswordRequest;
import com.bookstore.request.LoginRequest;
import com.bookstore.request.RegisterUser;
import com.bookstore.request.VerifyEmail;
import com.bookstore.response.GeneralResponse;
import com.bookstore.response.LoginResponse;
import com.bookstore.service.EmailService;
import com.bookstore.service.EmailVerificationRegistryService;
import com.bookstore.util.GenerateOTP;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class EmailVerificationImpl implements EmailVerificationRegistryService {


    private final RegistryRepository registryRepository;
    private final EmailService emailService;
    private final JwtService jwtService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final ForgetPasswordRepository forgetPasswordRepository;

    @Override
    public boolean generateOtp(VerifyEmail verifyEmail) {
        EmailVerificationRegistry user = registryRepository.findByEmail(verifyEmail.getEmail());

        // If user doesn't exist, create a new one
        if (user == null) {
            user = new EmailVerificationRegistry();
            user.setEmail(verifyEmail.getEmail());
            user.setOTP(GenerateOTP.generateOTP()); // ✅ OTP generation
            user.setROLE(verifyEmail.getRole());   // Set user role
            user.setCreatedDate(new Date());       // Set created date
            user.setRegistered(false);
            user.setEmailVerified(false);
            registryRepository.save(user);
            emailService.sendEmail(verifyEmail.getEmail(), "Email verification OTP","Email Verification!", user.getOTP());
            return true;
        }

        // If user exists but not registered, update the OTP
        if (!user.isRegistered()) {
            user.setEmailVerified(false);
            user.setROLE(verifyEmail.getRole());
            user.setOTP(GenerateOTP.generateOTP());
            user.setCreatedDate(new Date());
            registryRepository.save(user);
            emailService.sendEmail(verifyEmail.getEmail(), "Email verification OTP","Email Verification!",  user.getOTP());
            return true;
        }

        // If already registered, do not regenerate OTP
        return false;
    }

    @Override
    public boolean emailVerification(VerifyEmail verifyEmail) {
        EmailVerificationRegistry user = registryRepository.findByEmail(verifyEmail.getEmail());
        if(user==null){
            return false;
        }
        if(user.getOTP().equals(verifyEmail.getOtp()) || user.getROLE().equals(verifyEmail.getRole())){
            user.setEmailVerified(true);
            registryRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public String userRegistered(RegisterUser registerUser) {
        EmailVerificationRegistry user = registryRepository.findByEmail(registerUser.getEmail());
        if (user == null) {
            return "User not found or not pre-verified.";
        }

        if (user.isRegistered()) {
            return "Email already Registered. Registration not allowed.";
        }
        if(!user.isEmailVerified()){
            return "Email not verified. Registration not allowed. Please verify your email.";
        }

        Address address = new Address();
        user.setFullName(registerUser.getFullName());
        user.setRegistered(true);
        user.setPhoneNumber(registerUser.getPhoneNumber());
        user.setPassword(passwordEncoder.encode(registerUser.getPassword()));
        address.setLand(registerUser.getLand());
        address.setCity(registerUser.getCity());
        address.setState(registerUser.getState());
        address.setPin(registerUser.getPin());
        user.setAddress(address);

//        String token = jwtService.generateToken(user.getEmail());
        registryRepository.save(user);
        return "Register successful.";
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        EmailVerificationRegistry user=registryRepository.findByEmail(loginRequest.getEmail());
        LoginResponse loginResponse=new LoginResponse();

        if(user==null){
            loginResponse.setSuccessful(false);
            return loginResponse;
        }
        if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword()) && user.getROLE().equals(loginRequest.getRole())) {
//            System.out.println(loginRequest);
            loginResponse.setEmailVerificationRegistry(user);
            loginResponse.setToken(jwtService.generateToken(user.getEmail()));
            loginResponse.setSuccessful(true);
            return loginResponse;
        }

        loginResponse.setSuccessful(false);
        return loginResponse;
    }

    @Override
    public GeneralResponse forgetPasswordGenerateOTP(ForgetPasswordRequest forgetPasswordRequest) {
//        System.out.println(forgetPasswordRequest);
        EmailVerificationRegistry user=registryRepository.findByEmail(forgetPasswordRequest.getEmail());
        GeneralResponse generalResponse=new GeneralResponse();
        ForgetPassword forgetPassword=forgetPasswordRepository.findByEmail(forgetPasswordRequest.getEmail());
        if(user==null){
            generalResponse.setMessage("Email not found!");
            return generalResponse;
        }
        if(user.getROLE().equals(forgetPasswordRequest.getRole())){
            forgetPassword.setOtp(GenerateOTP.generateOTP());
            forgetPassword.setEmailVerificationRegistry(user);
            forgetPassword.setEmail(user.getEmail());
            forgetPasswordRepository.save(forgetPassword);
            generalResponse.setSuccessful(true);
            generalResponse.setEmail(user.getEmail());
            generalResponse.setMessage("OTP Generated successful!");
            emailService.sendEmail(user.getEmail(),"Password reset OTP","Reset password verification!",forgetPassword.getOtp());
            return generalResponse;
        }
        generalResponse.setMessage("Something want wrong!");
        return generalResponse;
    }

    @Override
    public GeneralResponse resetPassword(ForgetPasswordRequest forgetPasswordRequest) {
        ForgetPassword forgetPassword=forgetPasswordRepository.findByEmail(forgetPasswordRequest.getEmail());
        EmailVerificationRegistry user=registryRepository.findByEmail(forgetPasswordRequest.getEmail());
        GeneralResponse generalResponse=new GeneralResponse();
        if(forgetPassword==null && user==null){
            generalResponse.setMessage("Invalid email. PLease enter correct email!");
            return generalResponse;
        }
        if(forgetPasswordRequest.getOtp().equals(Objects.requireNonNull(forgetPassword).getOtp()) && forgetPasswordRequest.getRole().equals(user.getROLE())){
            user.setPassword(passwordEncoder.encode(forgetPasswordRequest.getPassword()));
            registryRepository.save(user);
            generalResponse.setEmail(user.getEmail());
            generalResponse.setMessage("Password Updated successful!");
            generalResponse.setSuccessful(true);
            return generalResponse;
        }

        generalResponse.setMessage("Something want wrong!");
        return generalResponse;
    }


}
