/*
 * Copyright (c) 2023. Created by Kristina Barbina.
 */

import testFramework.TestProcessor;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @org.junit.jupiter.api.Test
    void ExecuteTestsInLoop() {
        for (int i = 0; i < 100; i++) {
            assertDoesNotThrow(()->TestProcessor.ExecuteTests(TestClass.class));
        }

    }
}