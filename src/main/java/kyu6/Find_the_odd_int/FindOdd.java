package kyu6.Find_the_odd_int;
import java.util.*;

/*
Given an array of integers, find the one that appears an odd number of times.

There will always be only one integer that appears an odd number of times.

Examples
[7] should return 7, because it occurs 1 time (which is odd).
[0] should return 0, because it occurs 1 time (which is odd).
[1,1,2] should return 2, because it occurs 1 time (which is odd).
[0,1,0,1,0] should return 0, because it occurs 3 times (which is odd).
[1,2,2,3,3,3,4,3,3,3,2,2,1] should return 4, because it appears 1 time (which is odd).
 */

public class FindOdd {
    public static int findIt(int[] a) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < a.length; i++){
            if(map.containsKey(a[i])){
                map.replace(a[i], map.get(a[i]) + 1);
            }else{
                map.put(a[i], 1);
            }
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()){
            if(entry.getValue() % 2 != 0){
                return entry.getKey();
            }
        }
        return 0;
    }
}