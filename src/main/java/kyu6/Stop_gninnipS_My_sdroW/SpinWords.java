package kyu6.Stop_gninnipS_My_sdroW;
/*
Write a function that takes in a string of one or more words, and returns the same string,
but with all five or more letter words reversed (Just like the name of this Kata).
Strings passed in will consist of only letters and spaces. Spaces will be included only
when more than one word is present.
 */
public class SpinWords {
    public String spinWords(String sentence) {
        StringBuilder result = new StringBuilder();
        for (String word : sentence.split(" ")) {
            StringBuilder toAppend = new StringBuilder(word);
            if (toAppend.length() > 4) {
                result.append(toAppend.reverse()).append(" ");
            } else {
                result.append(toAppend).append(" ");
            }
        }
        return result.toString().trim();
    }
}
