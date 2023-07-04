package kyu6.Longest_palindrome;

/*
Find the length of the longest substring in the given string s that is the same in reverse.

As an example, if the input was “I like racecars that go fast”, the substring (racecar) length would be 7.

If the length of the input string is 0, the return value must be 0.

Example:
"a" -> 1
"aab" -> 2
"abcde" -> 1
"zzbaabcd" -> 4
"" -> 0
 */

public class Palindromes {
    public static int longestPalindrome(final String s) {
        int length = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = s.length(); j > i; j--) {
                String substring = s.substring(i, j);
                String reversed = new StringBuilder(substring).reverse().toString();
                if (substring.equals(reversed)) {
                    length = Math.max(substring.length(), length);
                }
            }
        }
        return length;
    }
}
