package kyu4.Sum_of_Intervals;
import java.util.Arrays;
/*
Write a function called sumIntervals/sum_intervals that accepts an array of intervals, and returns the sum of all the interval lengths. Overlapping intervals should only be counted once.

Intervals
Intervals are represented by a pair of integers in the form of an array. The first value of the interval will always be less than the second value. Interval example: [1, 5] is an interval from 1 to 5. The length of this interval is 4.

Overlapping Intervals
List containing overlapping intervals:

[
   [1, 4],
   [7, 10],
   [3, 5]
]
The sum of the lengths of these intervals is 7. Since [1, 4] and [3, 5] overlap, we can treat the interval as [1, 5], which has a length of 4.

Examples:
sumIntervals( [
   [1, 2],
   [6, 10],
   [11, 15]
] ) => 9

sumIntervals( [
   [1, 4],
   [7, 10],
   [3, 5]
] ) => 7

sumIntervals( [
   [1, 5],
   [10, 20],
   [1, 6],
   [16, 19],
   [5, 11]
] ) => 19

sumIntervals( [
   [0, 20],
   [-100000000, 10],
   [30, 40]
] ) => 100000030
Tests with large intervals
Your algorithm should be able to handle large intervals. All tested intervals are subsets of the range [-1000000000, 1000000000].
 */
public class Interval {
    private static Boolean[] positiveNumbers = new Boolean[10000];
    private static Boolean[] negativeNumbers = new Boolean[10000];

    public static int sumIntervals(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        Arrays.fill(positiveNumbers, false);
        Arrays.fill(negativeNumbers, false);
        for (int[] interval : intervals) {
            for (int i = interval[0] + 1; i <= interval[1]; i++) {
                if (i < 0) {
                    negativeNumbers[i * -1] = true;
                }else {
                    positiveNumbers[i] = true;
                }
            }
        }
        int result = findTotalLength();
        Arrays.fill(positiveNumbers, false);
        Arrays.fill(negativeNumbers, false);
        return result;
    }
    private static int findTotalLength() {
        return (int) Arrays.stream(positiveNumbers).filter(e -> e).count()
                + (int) Arrays.stream(negativeNumbers).filter(e -> e).count();
    }
}