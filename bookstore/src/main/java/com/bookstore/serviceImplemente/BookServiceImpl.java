package com.bookstore.serviceImplemente;

import com.bookstore.exception.BookNotFoundException;
import com.bookstore.model.Book;
import com.bookstore.repository.BookRepository;
import com.bookstore.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;

    @Override
    public boolean updateBook(Book book, Long id) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Book not found with id: " + id));

        existingBook.setBookName(book.getBookName());
        existingBook.setCategory(book.getCategory());
        existingBook.setLanguage(book.getLanguage());
        existingBook.setDescription(book.getDescription());
        existingBook.setOfferPrice(book.getOfferPrice());
        existingBook.setPrice(book.getPrice());
        existingBook.setCategory(book.getCategory());
        existingBook.setNumberOfCount(book.getNumberOfCount());
        existingBook.setBookImage(book.getBookImage());
        bookRepository.save(existingBook);
        return true;
    }

    @Override
    public List<Book> getAllBook() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found with id: " + id));
    }

    int add(int a,int b){
        return 1;
    }
    int add(int b){
        return 1;
    }


}
