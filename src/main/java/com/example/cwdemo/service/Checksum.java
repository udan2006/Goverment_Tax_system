package com.example.cwdemo.service;

public class Checksum {
    public int calculateChecksum(String input) {
        int upperCase = 0;
        int lowerCase = 0;
        int numberAndDots = 0;

        for (char c : input.toCharArray()) {
            if(Character.isUpperCase(c)){
                upperCase++;
            } else if (Character.isLowerCase(c)) {
                lowerCase++;
            } else if (Character.isDigit(c) || c == '.') {
                numberAndDots++;
            }
        }
        return upperCase + lowerCase + numberAndDots;
    }
}
