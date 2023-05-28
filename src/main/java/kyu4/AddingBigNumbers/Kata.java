package kyu4.AddingBigNumbers;

/*
We need to sum big numbers and we require your help.

Write a function that returns the sum of two numbers. The input numbers are strings and the function must return a string.

Example
add("123", "321"); -> "444"
add("11", "99");   -> "110"
Notes
The input numbers are big.
The input is a string of only digits
The numbers are positives
 */
public class Kata {
    public static String add(String a, String b) {

        StringBuilder result = new StringBuilder();
        String small = a.length() <= b.length() ? a : b;
        String big = b.length() >= a.length() ? b : a;


        int sIndex = small.length() - 1;
        int bIndex = big.length() - 1;
        int temp = 0;

        while(bIndex >= 0){
            int toAppend;
            if(sIndex < 0){
                toAppend = temp + big.charAt(bIndex) - 48;
                temp = 0;
            }else{
                toAppend = small.charAt(sIndex) - 48 + big.charAt(bIndex) - 48;
            }
            if(temp > 0){
                toAppend += temp;
                temp = 0;
            }
            if(toAppend < 10){
                result.append(toAppend);
            }else{
                temp = toAppend / 10;
                result.append(toAppend % 10);
            }
            sIndex--;
            bIndex--;
        }
        if(temp > 0){
            result.append(temp);
        }

        return result.reverse().toString().replaceFirst("^0+", "");
    }
}