package kyu5.Count_IP_Addresses;
import java.util.Arrays;
import java.lang.Math;

/*
Implement a function that receives two IPv4 addresses, and returns the number of addresses between them (including the first one, excluding the last one).

All inputs will be valid IPv4 addresses in the form of strings. The last address will always be greater than the first one.

Examples
* With input "10.0.0.0", "10.0.0.50"  => return   50
* With input "10.0.0.0", "10.0.1.0"   => return  256
* With input "20.0.0.10", "20.0.1.0"  => return  246
 */

public class CountIPAddresses {

    public static long ipsBetween(String start, String end) {
        long[] result = new long[4];
        for(int i = 0; i < 4; i++) {
            result[i] = (long)Math.pow(256, 3 - i) *  (Long.parseLong(end.split("\\.")[i]) -  Long.parseLong(start.split("\\.")[i]));
        }
        return Arrays.stream(result).sum();
    }
}