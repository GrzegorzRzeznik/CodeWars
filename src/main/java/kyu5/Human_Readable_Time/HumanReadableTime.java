package kyu5.Human_Readable_Time;
/*
Write a function, which takes a non-negative integer (seconds) as input and returns the time in a human-readable format (HH:MM:SS)

HH = hours, padded to 2 digits, range: 00 - 99
MM = minutes, padded to 2 digits, range: 00 - 59
SS = seconds, padded to 2 digits, range: 00 - 59
The maximum time never exceeds 359999 (99:59:59)

You can find some examples in the test fixtures.
 */
public class HumanReadableTime {
    public static String makeReadable(int seconds) {
        String[] result = new String[3];
        int hours = seconds / 60 / 60;
        int minutes = seconds / 60 - hours * 60;
        int secs = seconds - hours * 3600 - minutes * 60;
        result[0] = String.valueOf(hours);
        result[1] = String.valueOf(minutes);
        result[2] = String.valueOf(secs);
        for (int i = 0; i < result.length; i++){
            if(result[i].length() < 2){
                result[i] = "0" + result[i];

            }
        }
        return result[0] +":" + result[1] + ":" + result[2];
    }
}