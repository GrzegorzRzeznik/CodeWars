package kyu5.Greed_is_Good;

import java.util.HashMap;
import java.util.Map;

/*
Greed is a dice game played with five six-sided dice. Your mission, should you choose to accept it,
is to score a throw according to these rules. You will always be given an array with five six-sided dice values.

 Three 1's => 1000 points
 Three 6's =>  600 points
 Three 5's =>  500 points
 Three 4's =>  400 points
 Three 3's =>  300 points
 Three 2's =>  200 points
 One   1   =>  100 points
 One   5   =>   50 point
A single die can only be counted once in each roll. For example, a given "5" can only count as part of
a triplet (contributing to the 500 points) or as a single 50 points, but not both in the same roll.

Example scoring

 Throw       Score
 ---------   ------------------
 5 1 3 4 1   250:  50 (for the 5) + 2 * 100 (for the 1s)
 1 1 1 3 1   1100: 1000 (for three 1s) + 100 (for the other 1)
 2 4 4 5 4   450:  400 (for three 4s) + 50 (for the 5)
 */
public class Greed {
    private static Map<Integer, Integer> map;
    private static int score;

    public static int greedy(int[] dice){
        map = new HashMap<>();
        score = 0;
        for (int i : dice) {
            add(i);
        }
        countTripples();
        countSingles();

        return score;
    }

    private static void countTripples(){
        Integer[] triples = map.entrySet().stream().filter(e -> e.getValue() > 2).map(Map.Entry::getKey).toArray(Integer[]::new);
        for (int i : triples) {
            if (i == 1){
                score += 1000;
                remove(i, 3);
            }else {
                score += i * 100;
                remove(i, 3);
            }
        }
    }

    private static void countSingles(){
        if(map.containsKey(5)) score += map.get(5) * 50;
        if(map.containsKey(1)) score += map.get(1) * 100;
    }

    private static void add(int n) {
        if(map.containsKey(n)){
            map.put(n, map.get(n) + 1);
        } else {
            map.put(n, 1);
        }
    }
    private static void remove(int n, int a) {
        if(map.containsKey(n)){
            map.put(n, map.get(n) - a);
        }
    }
}
