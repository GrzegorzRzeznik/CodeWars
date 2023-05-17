package kyu6.Binary_to_Text_ASCII_Conversion;
/*
Write a function that takes in a binary string and returns the equivalent decoded text (the text is ASCII encoded).

Each 8 bits on the binary string represent 1 character on the ASCII table.

The input string will always be a valid binary string.

Characters can be in the range from "00000000" to "11111111" (inclusive)

Note: In the case of an empty binary string your function should return an empty string.
 */

class Solution {
    public static String binaryToText(String binary) {
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < binary.length(); i+= 8){
            result.append((char) Integer.parseInt(binary.substring(i, i + 8), 2));
        }
        return result.toString();
    }
}