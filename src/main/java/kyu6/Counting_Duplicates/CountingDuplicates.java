package kyu6.Counting_Duplicates;
import java.util.*;

/*
Count the number of Duplicates
Write a function that will return the count of distinct case-insensitive alphabetic characters and numeric digits that occur more than once in the input string. The input string can be assumed to contain only alphabets (both uppercase and lowercase) and numeric digits.

Example
"abcde" -> 0 # no characters repeats more than once
"aabbcde" -> 2 # 'a' and 'b'
"aabBcde" -> 2 # 'a' occurs twice and 'b' twice (`b` and `B`)
"indivisibility" -> 1 # 'i' occurs six times
"Indivisibilities" -> 2 # 'i' occurs seven times and 's' occurs twice
"aA11" -> 2 # 'a' and '1'
"ABBA" -> 2 # 'A' and 'B' each occur twice
 */
public class CountingDuplicates {
    public static int duplicateCount(String text) {
        String input = text.toLowerCase();
        Set<Character> set = new HashSet<>();
        char checked;
        for (int i = 0; i < input.length(); i++){
            checked = input.charAt(i);
            for (int j = i + 1; j < input.length(); j++){
                if(input.charAt(j) == checked){
                    Character c = input.charAt(j);
                    set.add(c);
                }
            }
        }
        return set.size();
    }
}