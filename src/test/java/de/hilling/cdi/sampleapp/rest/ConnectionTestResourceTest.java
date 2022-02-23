package de.hilling.cdi.sampleapp.rest;

import jakarta.inject.Inject;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import de.hilling.junit.cdi.CdiTestJunitExtension;
import de.hilling.junit.cdi.microprofile.ConfigPropertyValue;

@ExtendWith(CdiTestJunitExtension.class)
class ConnectionTestResourceTest {

    @Inject
    private ConnectionTestResource connectionTestResource;

    @Test
    void testDefaultAnswer() {
        assertEquals("hello, world!", connectionTestResource.ping());
    }

    @Test
    @ConfigPropertyValue(name = "cdi-test-demo.pong", value = "hello, test!")
    void testOverriddenAnswer() {
        assertEquals("hello, test!", connectionTestResource.ping());
    }
}