package de.hilling.cdi.sampleapp.service;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTimeout;

import java.util.Arrays;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.extension.ExtendWith;

import de.hilling.cdi.sampleapp.jpa.Book;
import de.hilling.cdi.sampleapp.testsupport.BookBaseTest;
import de.hilling.junit.cdi.CdiTestJunitExtension;

/**
 * Demo for running lots of (generated) junit 5 tests _fast_.
 */
@ExtendWith(CdiTestJunitExtension.class)
class ManyTestsBookServiceTest extends BookBaseTest {

    @Inject
    private BookService bookService;

    @Inject
    private EntityManager entityManager;

    @TestFactory
    Stream<? extends DynamicTest> dynamicTestsForStoring() {
        Random random = new Random();
        IntStream characters = random.ints(50, 'a', 'z' + 1);
        int[] numbers = random.ints(10, 1000, 2000).toArray();

        return characters.mapToObj(c -> Arrays.stream(numbers).mapToObj(n -> ((char)c) + "-" + n))
                         .flatMap(Function.identity())
                         .map(this::createTest);
    }

    void storeAndLoadBook(Book book) {
        long id = bookService.saveBook(book);
        entityManager.flush();
        entityManager.clear();
        Book firstReloaded = bookService.getBookById(id);
        assertEquals(firstReloaded.getTitle(), book.getTitle());
    }

    private DynamicTest createTest(String title) {
        return DynamicTest.dynamicTest("Testing: " + title, () -> {
            Book testBook = new Book();
            testBook.setTitle(title);
            storeAndLoadBook(testBook);
        });
    }
}