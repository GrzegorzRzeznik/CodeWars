package kyu6.Columnize;

import java.util.HashMap;
import java.util.Map;

/*
DESCRIPTION:
You are given an array of strings that need to be spread among N columns. Each column's width should be the same as the length of the longest string inside it.

Separate columns with " | ", and lines with "\n"; content should be left-justified.

{"1", "12", "123", "1234", "12345", "123456"} should become:

1
12
123
1234
12345
123456
for 1 column,

1     | 12
123   | 1234
12345 | 123456
for 2 columns,

1     | 12     | 123 | 1234
12345 | 123456
for 4 columns.
 */
public class Kata {

    public static String columnize(String[] input, int numberOfColumns) {
        StringBuilder result = new StringBuilder();
        Map<Integer, Integer> columnLengths = findColumnLengths(input, numberOfColumns);

        int column = 1;
        for (int i = 0; i < input.length; i++) {
            String format = columnLengths.get(column) == 0 ? "" : "%-" + columnLengths.get(column) + "s";
            result.append(String.format(format, input[i]));
            if (column < numberOfColumns && i < input.length - 1) {
                result.append(" | ");
                column++;
                continue;
            }
            if (column == numberOfColumns && i < input.length - 1) {
                result.append("\n");
                column = 1;
            }
        }
        return result.toString();
    }

    private static Map<Integer, Integer> findColumnLengths(String[] input, int numberOfColumns) {
        Map<Integer, Integer> result = new HashMap<>();
        for (int i = 0; i < numberOfColumns; i++) {
            int max = 0;
            for (int j = i; j < input.length; j += numberOfColumns) {
                max = Math.max(max, input[j].length());
            }
            result.put(i + 1, max);
        }
        return result;
    }
}
