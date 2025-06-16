package com.bookstore.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class VendorDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String phoneNumber;
    private String email;
    private String gstnnumber;
    private String brand;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "vendorDetails_id")
    private List<Book> books;
    private LocalDateTime createdDate;
}
