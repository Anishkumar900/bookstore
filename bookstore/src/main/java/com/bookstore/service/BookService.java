package com.bookstore.service;

import com.bookstore.model.Book;

import java.util.List;

public interface BookService {
    boolean updateBook(Book book,Long id);
    List<Book> getAllBook();
    Book getBookById(Long id) throws Exception;
}
