package kyu6.Popping_Balloons;
import java.util.*;
import java.util.stream.Collectors;

/*
Sami is practicing her aim with her bow and is shooting some balloons in the air. On each balloon,
they have different numbers written on them which represent their size. She would like to pop the balloon highest
in the air that also has the most balloons of the same size present.
If there is a tie, then she will instead pop the balloon of the size highest in the air.
Do you think you can output which balloon she pops on each shot?

You will be provided an array of the balloons as integers (the integers representing the sizes) in lowest to highest order in the air.
You will also be given an integer pops, which will be the number of pops that Sami will execute.

Constraints
0 < pops <= number of elements in balloons

10 <= number of elements in balloons <= 500

1 <= balloon size <= 1000

Example
pops = 4
balloons = [5, 7, 5, 7, 4, 5]

pop #1: 5
pop #2: 7
pop #3: 5
pop #4: 4

return: [5, 7, 5, 4]
Explanation
For pop #1, we return 5 because it is the most frequent, the list now becomes {5, 7, 5, 7, 4}. In pop #2, we return 7, since 5 and 7 are the most frequent, but 7 is the highest, so we pop 7. The list now becomes {5, 7, 5, 4}. In pop #3, we pop 5 since it’s the most frequent. The list now becomes {5, 7, 4}. In pop #4, we pop 4 since all balloons now have the same count (here: 1), but the balloon of size 4 is the highest in the air.
 */

public class Balloon {
    public static ArrayList<Integer> poppingBalloons(int pops, int[] balloons) {
        ArrayList<Integer> result = new ArrayList<>();
        LinkedList<Integer> remainingBalloons = new LinkedList<>();
        Map<Integer, Integer> sizeFrequency = new HashMap<>();
        for (int i : balloons) {
            remainingBalloons.add(i);
            addBalloonToMap(sizeFrequency, i);
        }
        for (int i = 0; i < pops; i++) {
            Integer nextToPop = findNextToPop(remainingBalloons, sizeFrequency);
            result.add(nextToPop);
            removeBalloonFromMap(sizeFrequency,nextToPop);
            remainingBalloons.removeLastOccurrence(nextToPop);
        }

        return result;
    }

    private static void removeBalloonFromMap(Map<Integer, Integer> sizeFrequency, Integer balloon) {
        if (sizeFrequency.get(balloon) > 1){
            sizeFrequency.replace(balloon, sizeFrequency.get(balloon) -1);
        } else {
            sizeFrequency.remove(balloon);
        }
    }

    private static Integer findNextToPop(LinkedList<Integer> remainingBalloons, Map<Integer, Integer> sizeFrequency) {
        int biggestFrequency = sizeFrequency.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getValue).get();
        List<Integer> mostFrequentBalloons = sizeFrequency.entrySet()
                .stream()
                .filter(e -> e.getValue().equals(biggestFrequency))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        for (int i = remainingBalloons.size() - 1; i >= 0; i--) {
            if (mostFrequentBalloons.contains(remainingBalloons.get(i))){
                return remainingBalloons.get(i);
            }
        }
        return null;
    }

    private static void addBalloonToMap(Map<Integer, Integer> map, Integer i) {
        if (map.containsKey(i)) {
            map.put(i, map.get(i) + 1);
        } else {
            map.put(i, 1);
        }
    }
}