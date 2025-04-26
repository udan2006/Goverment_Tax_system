package com.example.cwdemo;

import com.example.cwdemo.service.Checksum;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckSumTest {

    @Test
    public void testCheckSumAllCapital() {
        Checksum checksum = new Checksum();

        String input = "HELLO";

        int expectedResult = 5;
        int actualValue = checksum.calculateChecksum(input);

        assertEquals(expectedResult, actualValue);
    }

    @Test
    public void testCheckSumAllNumbersAndDots() {
        Checksum checksum = new Checksum();
        String input = "12.34.56.7";

        int expectedResult = 10;
        int actualValue = checksum.calculateChecksum(input);

        assertEquals(expectedResult, actualValue);
    }

    @Test
    public void testCheckSumAllLowerCase() {
        Checksum checksum = new Checksum();
        String input = "hello world";

        int expectedResult = 10;
        int actualValue = checksum.calculateChecksum(input);

        assertEquals(expectedResult, actualValue);
    }

    @Test
    public void testCheckSumMixedInput() {
        Checksum checksum = new Checksum();
        String input = "Hello World 123.456";

        int expectedResult = 17;
        int actualValue = checksum.calculateChecksum(input);

        assertEquals(expectedResult, actualValue);
    }
}
