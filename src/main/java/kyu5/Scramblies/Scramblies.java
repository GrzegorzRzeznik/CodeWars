package kyu5.Scramblies;

import java.util.HashMap;
import java.util.Map;

/*
Complete the function scramble(str1, str2) that returns true if
a portion of str1 characters can be rearranged to match str2, otherwise returns false.

Notes:

Only lower case letters will be used (a-z). No punctuation or digits will be included.
Performance needs to be considered.
Examples
scramble('rkqodlw', 'world') ==> True
scramble('cedewaraaossoqqyt', 'codewars') ==> True
scramble('katas', 'steak') ==> False
 */


public class Scramblies {
    public static boolean scramble(String str1, String str2) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : str1.toCharArray()) {
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }

        for (char c : str2.toCharArray()) {
            if (map.containsKey(c)) {
                if (map.get(c) > 1) {
                    map.put(c, map.get(c) - 1);
                } else {
                    map.remove(c);
                }
            } else {
                return false;
            }
        }
        return true;

    }
}
