package com.bookstore.serviceImplemente;

import com.bookstore.model.Book;
import com.bookstore.model.VendorDetails;
import com.bookstore.repository.BookRepository;
import com.bookstore.repository.VendorRepository;
import com.bookstore.service.VendorService;
import com.bookstore.util.BookOfferPrice;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;


@Service
@RequiredArgsConstructor
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;
    private final BookRepository bookRepository;
    @Override
    public VendorDetails createBook(VendorDetails vendorDetails) {
        VendorDetails existVendorDetails=vendorRepository.findByEmail(vendorDetails.getEmail());
        List<Book> newBook=vendorDetails.getBooks();
        float price,discountPercentage,discountPrice;
        for(Book book:newBook){
             price=book.getPrice();
             discountPercentage=book.getPercentageOFF();
             discountPrice=BookOfferPrice.discountPrice(price,discountPercentage);
            book.setOfferPrice(discountPrice);
        }

        vendorDetails.setBooks(newBook);


        if(existVendorDetails==null){
//            vendorDetails.setCreatedDate(LocalDateTime.now());

            return vendorRepository.save(vendorDetails);
        }
        if(existVendorDetails.getBooks()==null){
            existVendorDetails.setBooks(vendorDetails.getBooks());
        }
        else {
            existVendorDetails.getBooks().addAll(vendorDetails.getBooks());
        }

        return vendorRepository.save(existVendorDetails);
    }

    @Override
    public boolean updateVendor(VendorDetails vendorDetails) {
        VendorDetails existVendor=vendorRepository.findByEmail(vendorDetails.getEmail());
        if (existVendor == null) {
            return false;
        }
        existVendor.setName(vendorDetails.getName());
        existVendor.setBrand(vendorDetails.getBrand());
        existVendor.setGstnnumber(vendorDetails.getGstnnumber());
        existVendor.setPhoneNumber(vendorDetails.getPhoneNumber());
        vendorRepository.save(existVendor);
        return true;
    }

//    @Override
//    public List<VendorDetails> getAll() {
//        VendorDetails List<vendorDetails>=vendorRepository.findAll();
//        return vendorDetails;
//    }


}
