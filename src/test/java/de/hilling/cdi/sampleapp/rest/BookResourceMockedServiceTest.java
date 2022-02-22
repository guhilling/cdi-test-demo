package de.hilling.cdi.sampleapp.rest;

import jakarta.inject.Inject;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import de.hilling.cdi.sampleapp.jpa.Book;
import de.hilling.cdi.sampleapp.service.BookService;
import de.hilling.cdi.sampleapp.testsupport.BookBaseTest;
import de.hilling.junit.cdi.CdiTestJunitExtension;

/**
 * Test the {@link BookResource} service but mock the backend implementation
 * {@link BookService}.
 */
@ExtendWith(CdiTestJunitExtension.class)
@ExtendWith(MockitoExtension.class)
class BookResourceMockedServiceTest extends BookBaseTest {

    @Inject
    private BookResource bookResource;

    /**
     * This mock will automatically be used for all occurrences of {@link BookService} for all the tests
     * in this class.
     */
    @Mock
    private BookService bookService;

    @Test
    void storeBook() {
        bookResource.addBook(firstBook);
        verify(bookService).saveBook(firstBook);
        verifyNoMoreInteractions(bookService);
    }

    @Test
    void execptionInBackend() {
        when(bookService.saveBook(any(Book.class))).thenThrow(new PersistenceException("test error"));
        PersistenceException expected = assertThrows(PersistenceException.class, () -> {
            bookResource.addBook(firstBook);
        });
        assertEquals("test error", expected.getMessage());
    }

}