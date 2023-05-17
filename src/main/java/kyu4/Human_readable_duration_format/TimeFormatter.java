package kyu4.Human_readable_duration_format;
import java.util.ArrayList;
/*
Your task in order to complete this Kata is to write a function which formats a duration, given as a number of seconds, in a human-friendly way.

The function must accept a non-negative integer. If it is zero, it just returns "now".
Otherwise, the duration is expressed as a combination ofyears, days, hours, minutes and seconds.

It is much easier to understand with an example:

* For seconds = 62, your function should return
    "1 minute and 2 seconds"
* For seconds = 3662, your function should return
    "1 hour, 1 minute and 2 seconds"
For the purpose of this Kata, a year is 365 days and a day is 24 hours.

Note that spaces are important.

Detailed rules
The resulting expression is made of components like 4 seconds, 1 year, etc.
In general, a positive integer and one of the valid units of time, separated by a space.
The unit of time is used in plural if the integer is greater than 1.

The components are separated by a comma and a space (", "). Except the last component, which is separated by " and ", just like it would be written in English.

A more significant units of time will occur before than a least significant one. Therefore, 1 second and 1 year is not correct, but 1 year and 1 second is.

Different components have different unit of times. So there is not repeated units like in 5 seconds and 1 second.

A component will not appear at all if its value happens to be zero. Hence, 1 minute and 0 seconds is not valid, but it should be just 1 minute.

A unit of time must be used "as much as possible". It means that the function should not return 61 seconds, but 1 minute and 1 second instead.
Formally, the duration specified by of a component must not be greater than any valid more significant unit of time.
 */
public class TimeFormatter {

    private static final int MINUTE = 60;
    private static final int HOUR = 3600;
    private static final int DAY = 86400;
    private static final int YEAR = 31536000;

    public static String formatDuration(int seconds) {
        int[] components = countComponents(seconds);

        return formatComponents(components);
    }
    private static String formatComponents(int[] components) {
        StringBuilder sb = new StringBuilder();
        ArrayList<String> list = createComponentList(components);
        if(list.size() == 1){
            return list.get(0);
        }
        for (int i = 0; i < list.size(); i++) {
            if (i == list.size()-2 ) {
                sb.append(list.get(i)).append(" and ").append(list.get(i + 1));
                break;
            }else {
                sb.append(list.get(i)).append(", ");
            }
        }
        return sb.toString();

    }
    private static ArrayList<String> createComponentList(int[] components) {
        ArrayList<String> list = new ArrayList<>();
        if (components[0] == 1) {
            list.add(components[0] + " year");
        }
        if (components[0] > 1) {
            list.add(components[0] + " years");
        }
        if (components[1] == 1) {
            list.add(components[1] + " day");
        }
        if (components[1] > 1) {
            list.add(components[1] + " days");
        }
        if (components[2] == 1) {
            list.add(components[2] + " hour");
        }
        if (components[2] > 1) {
            list.add(components[2] + " hours");
        }
        if (components[3] == 1) {
            list.add(components[3] + " minute");
        }
        if (components[3] > 1) {
            list.add(components[3] + " minutes");
        }
        if (components[4] == 1) {
            list.add(components[4] + " second");
        }
        if (components[4] > 1) {
            list.add(components[4] + " seconds");
        }
        if (list.size() == 0 && components[4] == 0){
            list.add("now");
        }
        return list;
    }
    private static int[] countComponents(int seconds) {
        int[] components = new int[5];
        int time = seconds;
        components[0] = time / YEAR;
        time -= YEAR * components[0];
        components[1] = time / DAY;
        time -= DAY * components[1];
        components[2] = time / HOUR;
        time -= HOUR * components[2];
        components[3] = time / MINUTE;
        time -= MINUTE * components[3];
        components[4] = time;
        return components;
    }
}