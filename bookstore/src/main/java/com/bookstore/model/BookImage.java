package com.bookstore.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "BookImage")
public class BookImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String imageURL;
}
