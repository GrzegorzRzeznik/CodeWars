package kyu4.Find_the_nth_Reverse_Number;
import java.math.BigInteger;
/*
Reverse Number is a number which is the same when reversed.

For example, the first 20 Reverse Numbers are:

0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 22, 33, 44, 55, 66, 77, 88, 99, 101
TASK:

You need to return the nth reverse number. (Assume that reverse numbers start from 0 as shown in the example.)
NOTES:

1 < n <= 100000000000
 */

public class Palindrome {
    public static BigInteger findReverseNumber(long n) {
        if (n < 11) {
            return new BigInteger(String.valueOf(n - 1));
        }

        boolean oddResultLength = true;
        String number = String.valueOf(n);
        if (number.startsWith("10")){
            number = "9" + number.substring(2);
        }else if (number.startsWith("1")) {
            number = number.substring(1);
            oddResultLength = false;
        }else {
            char c = (char) (number.charAt(0) - 1);
            number = c + number.substring(1);
        }

        StringBuilder result = new StringBuilder(number);
        int loopStart = result.length() - (oddResultLength ? 2:1);
        for (int i = loopStart; i >= 0; i--) {
            result.append(result.charAt(i));
        }
        return new BigInteger(result.toString());
    }
}