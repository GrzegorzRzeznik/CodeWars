package kyu6.Triangle_number_check;
/*
A triangle number is a number where n objects form an equilateral triangle (it's a bit hard to explain). For example,
6 is a triangle number because you can arrange 6 objects into an equilateral triangle:

  1
 2 3
4 5 6

8 is not a triangle number because 8 objects do not form an equilateral triangle:

   1
  2 3
 4 5 6
7 8

In other words, the nth triangle number is equal to the sum of the n natural numbers from 1 to n.

Your task:
Check if a given input is a valid triangle number. Return true if it is, false if it is not (note that any non-integers,
including non-number types, are not triangle numbers).

You are encouraged to develop an effective algorithm: test cases include really big numbers.
 */
public class TriangleNumbers {

    public static Boolean isTriangleNumber(long number) {
        long sum = 0;
        for(long i = 1; i < number / 2 + 1; i++){
            sum += i;
            if (number == sum) return true;
            if (sum > number) return false;
        }
        return false;
    }
}
