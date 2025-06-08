package com.bookstore.model;


import com.bookstore.domain.USER_ROLE;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "email_verification_registry", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
})
public class EmailVerificationRegistry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // ✅ password will not be shown in responses

    private String password;
    private String fullName;
    private String OTP;
    @Enumerated(EnumType.STRING)
    private USER_ROLE ROLE = USER_ROLE.USER;
    private String phoneNumber;

    private boolean registered = false;
    private Date createdDate;


}
