package com.bookstore.serviceImplemente;

import com.bookstore.model.Book;
import com.bookstore.model.VendorDetails;
import com.bookstore.repository.VendorRepository;
import com.bookstore.service.VendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;
    @Override
    public VendorDetails createBook(VendorDetails vendorDetails) {
        VendorDetails existVendorDetails=vendorRepository.findByEmail(vendorDetails.getEmail());
//        Book book = new Book();
        if(existVendorDetails==null){
            vendorDetails.setCreatedDate(LocalDateTime.now());

//            book.setCreatedDate(LocalDateTime.now());
//            vendorDetails.setBooks(Collections.singletonList(book));
            return vendorRepository.save(vendorDetails);
        }
        if(existVendorDetails.getBooks()==null){
//            book.setCreatedDate(LocalDateTime.now());
//            vendorDetails.setBooks(Collections.singletonList(book));
            existVendorDetails.setBooks(vendorDetails.getBooks());
        }
        else {
//            book.setCreatedDate(LocalDateTime.now());
//            vendorDetails.setBooks(Collections.singletonList(book));
            existVendorDetails.getBooks().addAll(vendorDetails.getBooks());
        }

        return vendorRepository.save(existVendorDetails);
    }
}
