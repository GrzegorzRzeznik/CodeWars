package kyu6.Duplicate_Encoder;

/*
The goal of this exercise is to convert a string to a new string where each character in the new string is "(" if that character appears only once in the original string, or ")" if that character appears more than once in the original string. Ignore capitalization when determining if a character is a duplicate.

Examples
"din"      =>  "((("
"recede"   =>  "()()()"
"Success"  =>  ")())())"
"(( @"     =>  "))(("
Notes
Assertion messages may be unclear about what they display in some languages. If you read "...It Should encode XXX", the "XXX" is the expected result, not the input!
 */
public class DuplicateEncoder {
    static String encode(String word){
        String[] toEncode = word.toLowerCase().split("");
        StringBuilder result = new StringBuilder();
        for (String s : toEncode) {
            int occurrences = 0;
            for (int i = 0; i < toEncode.length; i++) {
                if (s.equals(toEncode[i])){
                    occurrences++;
                }
            }
            if (occurrences > 1){
                result.append(")");
            }else {
                result.append("(");
            }
        }
        return result.toString();
    }
}