package kyu6.PhoneWords;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*+
Given a string of numbers, you must perform a method in which you will translate this string into text, based on the below image:
https://upload.wikimedia.org/wikipedia/commons/thumb/7/73/Telephone-keypad2.svg/1024px-Telephone-keypad2.svg.png

For example if you get "22" return "b", if you get "222" you will return "c". If you get "2222" return "ca".

Further details:

0 is a space in the string.
1 is used to separate letters with the same number.
always transform the number to the letter with the maximum value, as long as it does not have a 1 in the middle. So, "777777" -->  "sq" and "7717777" --> "qs".
you cannot return digits.
Given a empty string, return empty string.
Return a lowercase string.
Examples:
"443355555566604466690277733099966688"  -->  "hello how are you"
"55282"                 -->  "kata"
"22266631339277717777"  -->  "codewars"
"66885551555"           -->  "null"
"833998"                -->  "text"
"000"                   -->  "   "
 */

public class PhoneWords {

    private static Map<Character, String> numbersCharacter = Map.of(
            '0', " ",
            '1', "",
            '2', "abc",
            '3', "def",
            '4', "ghi",
            '5', "jkl",
            '6', "mno",
            '7', "pqrs",
            '8', "tuv",
            '9', "wxyz");

    public static String phoneWords(String str) {
        // 416666663105558822255
        if (str == null || !str.matches("\\d+")){
            return "";
        }
        StringBuilder result = new StringBuilder("");
        List<String> words = new ArrayList<>();
        separateWords(str, words);
        for (String word : words) {
            if (!word.contains("1")) {
                result.append(numbersCharacter.get(word.charAt(0)).charAt(word.length() - 1));
            }
        }
        return result.toString();
    }

    private static void separateWords(String str, List<String> words) {
        int start = 0;
        char currentChar = str.charAt(0);
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != currentChar) {
                String word = str.substring(start, i);
                addWord(words, word);
                start = i;
                currentChar = str.charAt(i);
            }
            if (i == str.length() - 1) {
                addWord(words, str.substring(start));
            }
        }
    }

    private static void addWord(List<String> words, String word) {
        if (word.charAt(0) == '7' || word.charAt(0) == '9') {
            if (word.length() > 4) {
                words.add(word.substring(0, 4));
                addWord(words, word.substring(4));
            } else {
                words.add(word);
            }
        } else if (word.contains("0")) {
            for (int i = 0; i < word.length(); i++) {
                words.add("0");
            }
        }else {
            if (word.length() > 3) {
                words.add(word.substring(0, 3));
                addWord(words, word.substring(3));
            } else {
                words.add(word);
            }
        }
    }
}
