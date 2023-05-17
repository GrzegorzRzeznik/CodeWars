package kyu4.parseInt_reloaded;
import java.util.*;
import java.util.stream.Collectors;
/*
In this kata we want to convert a string into an integer. The strings simply represent the numbers in words.

Examples:

"one" => 1
"twenty" => 20
"two hundred forty-six" => 246
"seven hundred eighty-three thousand nine hundred and nineteen" => 783919
Additional Notes:

The minimum number is "zero" (inclusively)
The maximum number, which must be supported is 1 million (inclusively)
The "and" in e.g. "one hundred and twenty-four" is optional, in some cases it's present and in others it's not
All tested numbers are valid, you don't need to validate them
 */
public class Parser {
    private static final Map<String, Integer> namesNumbers = new HashMap<>();

    public static int parseInt(String numStr) {
        if(numStr.contains("million")){
            return 1000000;
        }
        int result = 0;
        fillMap();
        ArrayList<String> parts = (ArrayList<String>) Arrays.stream(numStr.replace(" and ", " ")
                .split(" ")).toList();

        int temp = 0;

        for (String s : parts) {
            if (s.equals("thousand")) {
                temp *= 1000;
                result += temp;
                temp = 0;
            }
            if (s.equals("hundred")) {
                temp *= 100;
            }
            if (s.contains("-")){
                String[] digits = s.split("-");
                temp += namesNumbers.get(digits[0]);
                temp += namesNumbers.get(digits[1]);
            }
            if (namesNumbers.containsKey(s)) {
                temp += namesNumbers.get(s);
            }
        }
        result += temp;
        return result;
    }
    private static void fillMap() {
        namesNumbers.put("zero", 0);
        namesNumbers.put("one", 1);
        namesNumbers.put("two", 2);
        namesNumbers.put("three", 3);
        namesNumbers.put("four", 4);
        namesNumbers.put("five", 5);
        namesNumbers.put("six", 6);
        namesNumbers.put("seven", 7);
        namesNumbers.put("eight", 8);
        namesNumbers.put("nine", 9);
        namesNumbers.put("ten", 10);
        namesNumbers.put("eleven", 11);
        namesNumbers.put("twelve", 12);
        namesNumbers.put("thirteen", 13);
        namesNumbers.put("fourteen", 14);
        namesNumbers.put("fifteen", 15);
        namesNumbers.put("sixteen", 16);
        namesNumbers.put("seventeen", 17);
        namesNumbers.put("eighteen", 18);
        namesNumbers.put("nineteen", 19);
        namesNumbers.put("twenty", 20);
        namesNumbers.put("thirty", 30);
        namesNumbers.put("forty", 40);
        namesNumbers.put("fifty", 50);
        namesNumbers.put("sixty", 60);
        namesNumbers.put("seventy", 70);
        namesNumbers.put("eighty", 80);
        namesNumbers.put("ninety", 90);
    }
}