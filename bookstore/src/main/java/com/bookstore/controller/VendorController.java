package com.bookstore.controller;

import com.bookstore.model.VendorDetails;
import com.bookstore.service.VendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class VendorController {

    private final VendorService vendorService;
    @PostMapping()
    public ResponseEntity<?> createBook(@RequestBody VendorDetails vendorDetails){
        VendorDetails newVendorDetails=vendorService.createBook(vendorDetails);
        return ResponseEntity.status(HttpStatus.OK).body(newVendorDetails);
    }
}
