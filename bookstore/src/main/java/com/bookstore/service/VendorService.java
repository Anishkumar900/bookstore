package com.bookstore.service;

import com.bookstore.model.VendorDetails;

import java.util.List;

public interface VendorService {
    VendorDetails createBook(VendorDetails vendorDetails);
    boolean updateVendor(VendorDetails vendorDetails);
//    List<VendorDetails> getAll();

}
