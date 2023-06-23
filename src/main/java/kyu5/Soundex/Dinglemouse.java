package kyu5.Soundex;
import java.util.*;
import java.util.stream.Collectors;

/*
In this Kata you will encode strings using a Soundex variation called "American Soundex" using
the following (case insensitive) steps:

Save the first letter. Remove all occurrences of h and w except first letter.
Replace all consonants (include the first letter) with digits as follows:
b, f, p, v = 1
c, g, j, k, q, s, x, z = 2
d, t = 3
l = 4
m, n = 5
r = 6
Replace all adjacent same digits with one digit.
Remove all occurrences of a, e, i, o, u, y except first letter.
If first symbol is a digit replace it with letter saved on step 1.
Append 3 zeros if result contains less than 3 digits. Remove all except first letter and 3 digits after it
Input
A space separated string of one or more names. E.g.

Sarah Connor

Output
Space separated string of equivalent Soundex codes (the first character of each code must be uppercase). E.g.
 */

public class Dinglemouse {

    private static final Map<String, String> consonantsReplacements = new HashMap<String, String>() {{
        put("bfpv", "1");
        put("cgjkqsxz", "2");
        put("dt", "3");
        put("l", "4");
        put("mn", "5");
        put("r", "6");
    }};

    public static String soundex(final String names) {

        String[] words = names.split(" ");
        List<String> result = new LinkedList<>();

        for (String s : words) {
            String firstLetter = s.substring(0, 1).toUpperCase();
            s = s.substring(0, 1) + s.substring(1).replaceAll("h", "").replaceAll("w", "");
            s = Arrays.stream(s.split(""))
                    .map(st -> replaceConsonants(st.toLowerCase()))
                    .collect(Collectors.joining());
            s = Arrays.stream(removeAdjacent(s).substring(1).split(""))
                    .filter(str -> !"aeiouy".contains(str))
                    .collect(Collectors.joining());
            s = firstLetter + s;

            if (s.matches("^[0-9].*")) {
                s = firstLetter.toUpperCase() + s.substring(1);
            }
            s = appendZeros(s);
            result.add(s);
        }
        return String.join(" ", result);
    }

    private static String replaceConsonants(String s) {
        for (Map.Entry<String, String> entry : consonantsReplacements.entrySet()) {
            if (entry.getKey().contains(s)) {
                return entry.getValue();
            }
        }
        return s;
    }

    private static String removeAdjacent(String s) {
        StringBuilder result = new StringBuilder();
        char previous = ' ';
        for(char c : s.toCharArray()) {
            if(c != previous) {
                result.append(c);
                previous = c;
            }
        }
        return result.toString();
    }

    private static String appendZeros(String s) {
        if(!s.matches("^[A-Z][0-9]{3}")) {
            s = s + "000";
        }
        return s.substring(0, 4);
    }
}
