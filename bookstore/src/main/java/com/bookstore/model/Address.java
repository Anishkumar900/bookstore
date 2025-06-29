package com.bookstore.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access =JsonProperty.Access.WRITE_ONLY)
    private Long id;
    private String land;
    private String city;
    private Long pin;
    private String state;
}
