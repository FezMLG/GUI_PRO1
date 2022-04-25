package com.fezmlg;

import org.junit.jupiter.api.*;

public class SampleTest {

    @BeforeAll
    public static void setupAll() {
        System.out.println("Should Print Before All Tests");
    }

    @BeforeEach
    public void setup() {
        System.out.println("Before each");
    }

    @Test
    @DisplayName("Should Create Contact")
    public void shouldCreateContact() {
        Assertions.assertEquals(1, 1);
    }

    @Nested
    class RepeatedTests {
        @DisplayName("Repeat Contact Creation Test 5 Times")
        @RepeatedTest(value = 5,
                name = "Repeating Contact Creation Test {currentRepetition} of {totalRepetitions}")
        public void shouldCreateContact() {
            Assertions.assertEquals(1, 1);
        }
    }

    @Test
    @DisplayName("Test Should Be Disabled")
    @Disabled
    public void shouldBeDisabled() {
        throw new RuntimeException("Test Should Not be executed");
    }


    @AfterEach
    public void tearDown() {
        System.out.println("Should Execute After Each Test");
    }

    @AfterAll
    public static void tearDownAll() {
        System.out.println("Should be executed at the end of the Test");
    }
}
