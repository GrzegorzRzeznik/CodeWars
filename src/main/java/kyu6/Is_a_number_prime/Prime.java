package kyu6.Is_a_number_prime;
import java.util.stream.IntStream;
/*
Define a function that takes an integer argument and returns a logical value true or false depending on if the integer is a prime.

Per Wikipedia, a prime number ( or a prime ) is a natural number greater than 1 that has no positive divisors other than 1 and itself.

Requirements
You can assume you will be given an integer input.
You can not assume that the integer will be only positive. You may be given negative numbers as well ( or 0 ).
NOTE on performance: There are no fancy optimizations required, but still the most trivial solutions might time out. Numbers go up to 2^31 ( or similar, depending on language ). Looping all the way up to n, or n/2, will be too slow.
Example
is_prime(1)  / false /
is_prime(2)  / true  /
is_prime(-1) / false /
*/

public class Prime {
    public static boolean isPrime(int num) {
        if(num <=1){
            return false;
        }
        if (num == 2){
            return true;
        }else{
            return (num % 2) != 0 && IntStream.rangeClosed(3, (int) Math.sqrt(num))
                    .filter(n -> n % 2 != 0)
                    .noneMatch(n -> (num % n == 0));
        }
    }
}