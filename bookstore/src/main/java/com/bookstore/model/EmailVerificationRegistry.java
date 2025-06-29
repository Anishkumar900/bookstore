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
    @JsonProperty(access =JsonProperty.Access.WRITE_ONLY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // ✅ password will not be shown in responses
    private String password;
    private String fullName;
    @JsonProperty(access =JsonProperty.Access.WRITE_ONLY)
    private String OTP;
    @Enumerated(EnumType.STRING)
    @JsonProperty(access =JsonProperty.Access.WRITE_ONLY)
    private USER_ROLE ROLE = USER_ROLE.USER;
    private String phoneNumber;
    @JsonProperty(access =JsonProperty.Access.WRITE_ONLY)
    private boolean emailVerified=false;
    @JsonProperty(access =JsonProperty.Access.WRITE_ONLY)
    private boolean registered = false;
    @JsonProperty(access =JsonProperty.Access.WRITE_ONLY)
//    @Column(name = "created_date", columnDefinition = "DATETIME")
////    private LocalDateTime createdDate;
//    private Date createdDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id") // Foreign key column
    private Address address;

}
