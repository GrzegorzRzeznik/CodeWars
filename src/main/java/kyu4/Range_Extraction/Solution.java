package kyu4.Range_Extraction;
import java.util.ArrayList;
import java.util.List;
/*
A format for expressing an ordered list of integers is to use a comma separated list of either

individual integers
or a range of integers denoted by the starting integer separated from the end integer in the range by a dash, '-'. The range includes all integers in the interval including both endpoints. It is not considered a range unless it spans at least 3 numbers. For example "12,13,15-17"
Complete the solution so that it takes a list of integers in increasing order and returns a correctly formatted string in the range format.

Example:

Solution.rangeExtraction(new int[] {-10, -9, -8, -6, -3, -2, -1, 0, 1, 3, 4, 5, 7, 8, 9, 10, 11, 14, 15, 17, 18, 19, 20})
# returns "-10--8,-6,-3-1,3-5,7-11,14,15,17-20"
 */
public class Solution {
    public static String rangeExtraction(int[] arr) {
        StringBuilder result = new StringBuilder();
        List<List<Integer>> ranges = new ArrayList<>();
        List<Integer> range = new ArrayList<>();
        range.add(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] - arr[i] == -1) {
                range.add(arr[i]);
            } else {
                ranges.add(List.copyOf(range));
                range.clear();
                range.add(arr[i]);
            }
        }
        ranges.add(range);
        for (List<Integer> list : ranges) {
            if (list.size() > 2) {
                result.append(list.get(0)).append("-").append(list.get(list.size() - 1)).append(",");
            }else {
                for (Integer i : list) {
                    result.append(i).append(",");
                }
            }
        }
        return result.substring(0, result.length() - 1);
    }
}