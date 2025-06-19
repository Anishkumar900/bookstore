package com.bookstore.controller;

import com.bookstore.model.Book;
import com.bookstore.model.VendorDetails;
import com.bookstore.service.BookService;
import com.bookstore.service.VendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class VendorController {

    private final VendorService vendorService;
    private final BookService bookService;
    @PostMapping()
    public ResponseEntity<?> createBook(@RequestBody VendorDetails vendorDetails){
        VendorDetails newVendorDetails=vendorService.createBook(vendorDetails);
        return ResponseEntity.status(HttpStatus.OK).body(newVendorDetails);
    }
    @PatchMapping()
    public ResponseEntity<?> updateVendor(@RequestBody VendorDetails vendorDetails){
        boolean result=vendorService.updateVendor(vendorDetails);
        if(!result){
            throw new IllegalArgumentException("Vendor not found with email: " + vendorDetails.getEmail());
        }
        return ResponseEntity.status(HttpStatus.OK).body(vendorDetails);

    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateBook(@RequestBody Book book ,@PathVariable Long id){
        boolean result=bookService.updateBook(book,id);
        if(!result){
            throw new IllegalArgumentException("Book id didn't found: "+id);
        }
        return ResponseEntity.status(HttpStatus.OK).body(book);

    }

    @GetMapping()
    public ResponseEntity<?> getAllBook(){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getAllBook());
    }
}
