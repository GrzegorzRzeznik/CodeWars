package kyu6.Stop_gninnipS_My_sdroW;

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
