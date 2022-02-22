package de.hilling.cdi.sampleapp.service;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import de.hilling.cdi.sampleapp.jpa.Book;
import de.hilling.cdi.sampleapp.testsupport.BookBaseTest;
import de.hilling.junit.cdi.CdiTestJunitExtension;

/**
 * Test the {@link BookService} backend implementation including jpa.
 * In the test scope the {@link EntityManager} will be provided by the
 * {@link de.hilling.cdi.sampleapp.testsupport.EntityManagerTestProducer}.
 */
@ExtendWith(CdiTestJunitExtension.class)
class BookServiceTest extends BookBaseTest {

    @Inject
    private BookService bookService;

    @Inject
    private EntityManager entityManager;

    @Test
    void storeBook() {
        long firstId = bookService.saveBook(firstBook);
        long secondId = bookService.saveBook(secondBook);
        assertNotEquals(firstId, secondId);
    }

    @Test
    void storeAndLoadBook() {
        long id = bookService.saveBook(firstBook);
        entityManager.flush();
        entityManager.clear();
        Book firstReloaded = bookService.getBookById(id);
        assertEquals(firstReloaded.getTitle(), firstBook.getTitle());
    }
}