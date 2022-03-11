package de.hilling.cdi.sampleapp.service;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;
import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import de.hilling.cdi.sampleapp.jpa.Book;
import de.hilling.cdi.sampleapp.testsupport.BookBaseTest;
import de.hilling.junit.cdi.CdiTestJunitExtension;

/**
 * Demo for running lots of (parameterized) junit 5 tests _fast_.
 */
@ExtendWith(CdiTestJunitExtension.class)
class ManyTestsBookServiceTest extends BookBaseTest {

    @Inject
    private BookService bookService;

    @Inject
    private EntityManager entityManager;

    static Stream<Book> createTestParams() {
        return Stream.
            generate(UUID::randomUUID).
            map(UUID::toString).
            map(Book::new).
            limit(50);
    }

    @ParameterizedTest
    @MethodSource("createTestParams")
    void storeAndLoadBook(Book book) {
        long id = bookService.saveBook(book);
        entityManager.flush();
        entityManager.clear();
        Book firstReloaded = bookService.getBookById(id);
        assertEquals(firstReloaded.getTitle(), book.getTitle());
        assertThat(bookService.getAllBooks(), hasSize(1));
    }

}