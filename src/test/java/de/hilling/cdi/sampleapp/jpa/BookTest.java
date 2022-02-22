package de.hilling.cdi.sampleapp.jpa;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import de.hilling.cdi.sampleapp.testsupport.BookBaseTest;

/**
 * Plain junit 5 test without using cdi-test.
 */
class BookTest extends BookBaseTest {

    private Jsonb jsonb = JsonbBuilder.create();

    @Test
    void createAndMarshall() {
        assertEquals("{\"id\":0,\"title\":\"It\"}", jsonb.toJson(firstBook));
    }
}