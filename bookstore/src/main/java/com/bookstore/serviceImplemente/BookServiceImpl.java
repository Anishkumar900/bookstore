package com.bookstore.serviceImplemente;

import com.bookstore.model.Book;
import com.bookstore.repository.BookRepository;
import com.bookstore.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

}
