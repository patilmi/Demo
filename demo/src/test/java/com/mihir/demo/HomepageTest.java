package com.mihir.demo;


import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class HomepageTest {
    Homepage hp = new Homepage();
    @Test
    public void test1() {
        int sum = hp.listAdd("7,-9,6");
        assertEquals(sum, 4);
    }

    @Test

    public void test2() {
        Exception exception = assertThrows(NumberFormatException.class, () -> {
            hp.listAdd("7,-9,a");
        });

        String expectedMessage = "For input string";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

}