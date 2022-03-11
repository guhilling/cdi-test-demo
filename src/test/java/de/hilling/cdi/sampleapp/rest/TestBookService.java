package de.hilling.cdi.sampleapp.rest;

import java.util.ArrayList;
import java.util.List;

import de.hilling.cdi.sampleapp.jpa.Book;
import de.hilling.cdi.sampleapp.service.BookService;
import de.hilling.junit.cdi.annotations.ActivatableTestImplementation;

/**
 * Test implementation for {@link BookService}.
 */
@ActivatableTestImplementation
public class TestBookService extends BookService {

    private final List<Book> books = new ArrayList<>();

    @Override
    public List<Book> getAllBooks() {
        return books;
    }

    public Book getBookById(long id) {
        return books.stream().filter(b -> b.getId() == id).findFirst().orElseThrow();
    }

    public long saveBook(Book book) {
        books.add(book);
        return book.getId();
    }

}
