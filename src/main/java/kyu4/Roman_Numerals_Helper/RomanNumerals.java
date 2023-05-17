package kyu4.Roman_Numerals_Helper;
import java.util.*;
import java.util.stream.Collectors;
/*
Write two functions that convert a roman numeral to and from an integer value. Multiple roman numeral values will be tested for each function.

Modern Roman numerals are written by expressing each digit separately starting with the left most digit and skipping any digit with a value of zero. In Roman numerals 1990 is rendered: 1000=M, 900=CM, 90=XC; resulting in MCMXC. 2008 is written as 2000=MM, 8=VIII; or MMVIII. 1666 uses each Roman symbol in descending order: MDCLXVI.

Input range : 1 <= n < 4000

In this kata 4 should be represented as IV, NOT as IIII (the "watchmaker's four").

Examples
to roman:
2000 -> "MM"
1666 -> "MDCLXVI"
1000 -> "M"
 400 -> "CD"
  90 -> "XC"
  40 -> "XL"
   1 -> "I"

from roman:
"MM"      -> 2000
"MDCLXVI" -> 1666
"M"       -> 1000
"CD"      -> 400
"XC"      -> 90
"XL"      -> 40
"I"       -> 1
 */
public class RomanNumerals {
    private static final LinkedHashMap<String, Integer> ROMAN_ARABIC = new LinkedHashMap<>();

    static {
        ROMAN_ARABIC.put("VIII", 8);
        ROMAN_ARABIC.put("VII", 7);
        ROMAN_ARABIC.put("IX", 9);
        ROMAN_ARABIC.put("VI", 6);
        ROMAN_ARABIC.put("IV", 4);
        ROMAN_ARABIC.put("III", 3);
        ROMAN_ARABIC.put("II", 2);
        ROMAN_ARABIC.put("V", 5);
        ROMAN_ARABIC.put("I", 1);
        ROMAN_ARABIC.put("LXXX", 80);
        ROMAN_ARABIC.put("LXX", 70);
        ROMAN_ARABIC.put("XC", 90);
        ROMAN_ARABIC.put("LX", 60);
        ROMAN_ARABIC.put("XL", 40);
        ROMAN_ARABIC.put("XXX", 30);
        ROMAN_ARABIC.put("XX", 20);
        ROMAN_ARABIC.put("L", 50);
        ROMAN_ARABIC.put("X", 10);
        ROMAN_ARABIC.put("DCCC", 800);
        ROMAN_ARABIC.put("DCC", 700);
        ROMAN_ARABIC.put("CM", 900);
        ROMAN_ARABIC.put("DC", 600);
        ROMAN_ARABIC.put("CD", 400);
        ROMAN_ARABIC.put("CCC", 300);
        ROMAN_ARABIC.put("CC", 200);
        ROMAN_ARABIC.put("D", 500);
        ROMAN_ARABIC.put("C", 100);
        ROMAN_ARABIC.put("MMM", 3000);
        ROMAN_ARABIC.put("MM", 2000);
        ROMAN_ARABIC.put("M", 1000);
    }
    public static String toRoman(int n) {
        List<String> digits = Arrays.stream(Integer.toString(n).split("")).collect(Collectors.toList());
        Collections.reverse(digits);
        StringBuilder result = new StringBuilder();
        int multiplier = 1;
        for (String s : digits) {
            String toInsert = findNext(multiplier, s).orElse("");
            result.insert(0, toInsert);
            multiplier = multiplier * 10;
        }
        return result.toString();
    }
    public static int fromRoman(String romanNumeral) {
        int result = 0;
        for (Map.Entry<String, Integer> e : ROMAN_ARABIC.entrySet()) {
            if (romanNumeral.contains(e.getKey())) {
                result += e.getValue();
                romanNumeral = romanNumeral.replace(e.getKey(), "");
            }
        }
        return result;
    }
    private static Optional<String> findNext(int multiplier, String s) {
        return ROMAN_ARABIC.entrySet().stream()
                .filter(e -> e.getValue().equals(Integer.parseInt(s) * multiplier))

                .map(Map.Entry::getKey).findFirst();
    }
}