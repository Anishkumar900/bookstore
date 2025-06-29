package com.bookstore.controller;

import com.bookstore.cloudService.CloudinaryService;
import com.bookstore.model.Book;
import com.bookstore.model.BookImage;
import com.bookstore.model.VendorDetails;
import com.bookstore.service.BookService;
import com.bookstore.service.VendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
@CrossOrigin
public class VendorController {

    private final VendorService vendorService;
    private final BookService bookService;
    private final CloudinaryService cloudinaryService;




    @PostMapping()
    public ResponseEntity<?> createBook(@RequestPart("vendor") VendorDetails vendorDetails,
                                        @RequestPart(value = "bookImages", required = false) MultipartFile bookImages)
    {

        try{
            String imageURL= cloudinaryService.uploadFile(bookImages);
            BookImage newBookImage=new BookImage();
            newBookImage.setImageURL(imageURL);
//            System.out.println(imageURL);
            if(!vendorDetails.getBooks().isEmpty()){
                vendorDetails.getBooks().get(0).setBookImage(List.of(newBookImage));
            }

                VendorDetails newVendorDetails=vendorService.createBook(vendorDetails);
                return ResponseEntity.status(HttpStatus.OK).body(newVendorDetails);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

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
