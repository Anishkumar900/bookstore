package com.bookstore.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "VendorDetails")
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
//    @Column(name = "created_date", columnDefinition = "DATETIME")
////    private LocalDateTime createdDate;
//    private LocalDateTime createdDate;
}
