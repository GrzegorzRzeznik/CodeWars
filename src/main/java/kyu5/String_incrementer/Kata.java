package kyu5.String_incrementer;

/*
Your job is to write a function which increments a string, to create a new string.

If the string already ends with a number, the number should be incremented by 1.
If the string does not end with a number. the number 1 should be appended to the new string.
Examples:

foo -> foo1

foobar23 -> foobar24

foo0042 -> foo0043

foo9 -> foo10

foo099 -> foo100

Attention: If the number has leading zeros the amount of digits should be considered.
 */
public class Kata {
    public static String incrementString(String str) {
        if (!str.matches(".*\\d$")) {
            return str + "1";
        }
        return increment(str);
    }

    private static String increment(String s) {
        char[] chars = s.toCharArray();
        int numberIndex = 0;
        for (int i = chars.length - 1; i >= 0; i--) {
            if (chars[i] < 48 || chars[i] > 57) {
                numberIndex = i + 1;
                break;
            }
            if (chars[i] == 57) {
                chars[i] = '0';
            } else {
                chars[i]++;
                break;
            }
        }
        String result = new String(chars);
        if (result.substring(numberIndex).matches("0*")) {
            return result.substring(0, numberIndex) + "1" + result.substring(numberIndex);
        }
        return new String(chars);
    }
}
