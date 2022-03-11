package de.hilling.cdi.sampleapp.rest;

import jakarta.inject.Inject;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasItem;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import de.hilling.cdi.sampleapp.service.BookService;
import de.hilling.cdi.sampleapp.testsupport.BookBaseTest;
import de.hilling.junit.cdi.CdiTestJunitExtension;

/**
 * Test the {@link BookResource} service but mock the backend implementation
 * {@link BookService}.
 */
@ExtendWith(CdiTestJunitExtension.class)
@ExtendWith(MockitoExtension.class)
class BookResourceComponentWithTestImplementationTest extends BookBaseTest {

    @Inject
    private BookResource bookResource;

    /**
     * This mock will automatically be used for all occurrences of {@link BookService} for all the tests
     * in this class.
     */
    @Inject
    private TestBookService bookService;

    @Test
    void emptyOnInitialCall() {
        assertThat(bookResource.getAllBooks(), empty());
    }

    @Test
    void storeBook() {
        bookResource.addBook(firstBook);
        assertThat(bookResource.getAllBooks(), hasItem(firstBook));
    }

}