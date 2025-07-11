package com.bookstore.serviceImplemente;

import com.bookstore.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender; // <-- Must be final for Lombok to inject

    @Override
    public void sendEmail(String to, String subject, String heading, String otp) {

        String htmlBody = """
            <html>
            <body style="font-family: Arial, sans-serif; background-color: #f4f4f4; padding: 20px;">
                <div style="max-width: 600px; margin: auto; background-color: white; padding: 30px; border-radius: 10px; box-shadow: 0px 0px 10px #cccccc;">
                    <h2 style="color: #333333;">%s</h2>
                    <p style="font-size: 16px; color: #555555;">
                        Thank you for registering BookStore! Please use the following One-Time Password (OTP) to verify your email address:
                    </p>
                    <p style="font-size: 24px; font-weight: bold; color: #007BFF;">%s</p>
                    <p style="font-size: 14px; color: #999999;">This OTP is valid for 10 minutes.</p>
                </div>
            </body>
            </html>
        """.formatted(heading,otp);

        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlBody, true); // true enables HTML

            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send email", e);
        }
    }


}
