package de.hilling.cdi.sampleapp.testsupport;

import org.junit.jupiter.api.BeforeEach;

import de.hilling.cdi.sampleapp.jpa.Book;

/**
 * Base class to provide common test data.
 */
public abstract class BookBaseTest {

    protected Book firstBook;
    protected Book secondBook;

    @BeforeEach
    void createBook() {
        firstBook = new Book();
        firstBook.setTitle("It");
        secondBook = new Book();
        secondBook.setTitle("Shining");
    }
}
