package com.isl.gbm.rbibond.util;

public class GenerateSequenceCAFU {

    public static String generateSequence(int count) {
        // Since the count starts from 1, subtract 1 to handle it in a zero-indexed manner.
        count--;

        // StringBuilder to build the result
        StringBuilder result = new StringBuilder();

        // Loop to process the sequence like a base-26 system
        while (count >= 0) {
            // Calculate the current character
            char currentChar = (char) ('A' + (count % 26));  // Modulo 26 to get the letter
            result.insert(0, currentChar);  // Insert the character at the beginning

            // Move to the next "digit" by dividing count by 26
            count /= 26;

            // If count is 0 and we're still in the loop, it means we need to stop
            if (count == 0) break;
        }

        // Pad with 'A' if the result length is less than 3 characters
        while (result.length() < 3) {
            result.insert(0, 'A');
        }

        return result.toString();
    }
}
