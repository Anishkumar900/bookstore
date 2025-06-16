package com.bookstore.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String bookName;
    private Float price;
    private String language;
    private String category;
//    private LocalDateTime createdDate;
    private Float percentageOFF;
    private Float offerPrice;
    private Long numberOfCount;
    @Column(length = 2000) // Optional: increase size for long descriptions
    private String description;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id") // Creates a foreign key in BookImage table
    private List<BookImage> bookImage;

}
