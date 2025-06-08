package com.bookstore.serviceImplemente;

import com.bookstore.model.EmailVerificationRegistry;
import com.bookstore.repository.RegistryRepository;
import com.bookstore.request.VerifyEmail;
import com.bookstore.service.EmailService;
import com.bookstore.service.EmailVerificationRegistryService;
import com.bookstore.util.GenerateOTP;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class EmailVerificationImpl implements EmailVerificationRegistryService {


    private final RegistryRepository registryRepository;
    private final EmailService emailService;

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
            registryRepository.save(user);
            emailService.sendEmail(verifyEmail.getEmail(), "Email verification OTP", "Your OTP is: " + user.getOTP());
            return true;
        }

        // If user exists but not registered, update the OTP
        if (!user.isRegistered()) {

            user.setROLE(verifyEmail.getRole());
            user.setOTP(GenerateOTP.generateOTP());
            user.setCreatedDate(new Date());
            registryRepository.save(user);
            emailService.sendEmail(verifyEmail.getEmail(), "Email verification OTP", "Your OTP is: " + user.getOTP());
            return true;
        }

        // If already registered, do not regenerate OTP
        return false;
    }
}
