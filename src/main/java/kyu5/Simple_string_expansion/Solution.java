package kyu5.Simple_string_expansion;
/*
Consider the following expansion:

solve("3(ab)") = "ababab"     -- because "ab" repeats 3 times
solve("2(a3(b))") = "abbbabbb" -- because "a3(b)" == "abbb", which repeats twice.
Given a string, return the expansion of that string.

Input will consist of only lowercase letters and numbers (1 to 9) in valid parenthesis. There will be no letters
or numbers after the last closing parenthesis.
 */

class Solution {
    public static String solve(String s) {
        StringBuilder result = new StringBuilder();
        int multiplier;
        int bracket = 0;
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                multiplier = (int) s.charAt(i) - 48;
                for (int j = i + 1; j < s.length(); j++) {
                    if (s.charAt(j) == 40) {
                        bracket++;
                    }
                    if (s.charAt(j) == 41) {
                        bracket--;
                    }
                    if (bracket == 0) {
                        result.append(s.substring(i + 2, j).repeat(multiplier));
                        multiplier = 1;
                        i = j;
                        j = s.length();
                    }
                }
            } else if (Character.isLetter(s.charAt(i))) {
                result.append(s.charAt(i));
            }
        }
        if (result.toString().contains("(")) {
            result = new StringBuilder(solve(result.toString()));
        }
        return result.toString();
    }

}