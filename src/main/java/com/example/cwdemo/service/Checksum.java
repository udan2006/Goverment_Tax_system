package com.example.cwdemo.service;
/** The checksum class provides a method to calculate a checksum value
 for a given transaction line string **/

public class Checksum {
    public int calculateChecksum(String input) {
        int upperCase = 0;  // count of Uppercase
        int lowerCase = 0;  // count of Lowercase
        int numberAndDots = 0;  // count of numbers and dots

        // convert input string to a character array and iterate through each character
        for (char c : input.toCharArray()) {
            if(Character.isUpperCase(c)){
                upperCase++;
            } else if (Character.isLowerCase(c)) {
                lowerCase++;
            } else if (Character.isDigit(c) || c == '.') {
                numberAndDots++;
            }
        }
        // Return the total checksum value.
        return upperCase + lowerCase + numberAndDots;
    }
}
