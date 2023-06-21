package kyu5.Second_Variation_on_Caesar_Cipher;

import java.util.*;
import java.util.stream.Collector;

/*
DESCRIPTION:
In this country soldiers are poor but they need a certain level of secrecy for their communications so,
though they do not know Caesar cypher, they reinvent it in the following way.

The action of a Caesar cipher is to replace each plaintext letter
(plaintext letters are from 'a' to 'z' or from 'A' to 'Z') with a different one a fixed number of places up or
down the alphabet. This displacement of a number of places is called the "shift" or the "rotate" of the message.
For example, if the shift is 1 letter a becomes b and A becomes B; the shift is cyclic so, with a shift of 1,
z becomes a and Z becomes A.

They use ASCII, without really knowing it, but code only letters a-z and A-Z. Other characters are kept such as.

They change the "rotate" each new message. This "rotate" is a prefix for their message once the message is coded.
The prefix is built of 2 letters, the second one being shifted from the first one by the "rotate", the first one is
the first letter, after being downcased, of the uncoded message.

For example if the "rotate" is 2, if the first letter of the uncoded message is 'J' the prefix should be 'jl'.

To lessen risk they cut the coded message and the prefix in five pieces since they have only five runners and each
runner has only one piece.

If possible the message will be evenly split between the five runners; if not possible, parts 1, 2, 3, 4 will be longer
and part 5 shorter. The fifth part can have length equal to the other ones or shorter. If there are many options
of how to split, choose the option where the fifth part has the longest length, provided that the previous conditions
are fulfilled. If the last part is the empty string don't put this empty string in the resulting array.

For example, if the coded message has a length of 17 the five parts will have lengths of 4, 4, 4, 4, 1.
The parts 1, 2, 3, 4 are evenly split and the last part of length 1 is shorter. If the length is 16 the parts
will be of lengths 4, 4, 4, 4, 0. Parts 1, 2, 3, 4 are evenly split and the fifth runner will stay at home since
his part is the empty string and is not kept.

Could you ease them in programming their coding?

Example with shift = 1 :

message : "I should have known that you would have a perfect answer for me!!!"

code : => ["ijJ tipvme ibw", "f lopxo uibu z", "pv xpvme ibwf ", "b qfsgfdu botx", "fs gps nf!!!"]

By the way, maybe could you give them a hand to decode?
*/
public class CaesarTwo {
    public static List<String> encodeStr(String s, int shift) {

        String encoded = s.chars()
                .map(i -> rotate(i, shift))
                .mapToObj(Character::toChars)
                .collect(Collector.of(StringBuilder::new,
                        StringBuilder::append,
                        StringBuilder::append,
                        StringBuilder::toString));

        encoded = createPrefix(s, shift) + encoded;
        return splitEncoded(encoded);
    }

    public static String decode(List<String> s) {

        int shift = (s.get(0).charAt(0) - s.get(0).charAt(1));
        String encoded = String.join("", s).substring(2);

        String decoded = encoded.chars()
                .map(i -> rotate(i, shift))
                .mapToObj(Character::toChars)
                .collect(Collector.of(StringBuilder::new,
                        StringBuilder::append,
                        StringBuilder::append,
                        StringBuilder::toString));

        return decoded;
    }

    private static int rotate(int i, int shift) {
        if ((i > 64 && i < 91) || (i > 96 && i < 123)) {
            int sum = i + shift;
            if (sum < 65 || (sum < 95 && i > 96)) {
                return sum + 26;
            }
            if (sum > 122 || (sum > 90 && i < 90)) {
                return sum - 26;
            }
            return sum;
        }
        return i;
    }

    private static List<String> splitEncoded(String encoded) {
        List<String> result = new ArrayList<>();
        int length = encoded.length() % 5 == 0 ? encoded.length() / 5 : 1 + encoded.length() / 5;
        for (int i = 0; i < 5; i++) {
            if (encoded.equals("")) {
                break;
            }
            if (encoded.length() < length) {
                result.add(encoded);
                break;
            }
            result.add(encoded.substring(0, length));
            encoded = encoded.substring(length);
        }
        return result;
    }

    private static String createPrefix(String s, int shift) {
        int firstLetter = (int) s.toLowerCase().charAt(0);
        char[] prefix = new char[]{(char) firstLetter, (char) (rotate(firstLetter, shift))};
        return new String(prefix);
    }
}
