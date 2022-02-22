package de.hilling.cdi.sampleapp.jpa;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import de.hilling.cdi.sampleapp.testsupport.BookBaseTest;
import de.hilling.junit.cdi.CdiTestJunitExtension;

/**
 * Test simple persistence using the {@link de.hilling.cdi.sampleapp.testsupport.EntityManagerTestProducer}
 * to provide the @{@link Inject}ed {@link EntityManager}
 */
@ExtendWith(CdiTestJunitExtension.class)
class BookPersistenceTest extends BookBaseTest {

    @Inject
    private EntityManager entityManager;

    @Test
    void storeBook() {
        entityManager.persist(firstBook);
        Assertions.assertNotEquals(0, firstBook.getId());
    }
}
