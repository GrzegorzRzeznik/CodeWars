package kyu6.Split_Strings;
import java.util.ArrayList;
import java.util.List;

/*
Complete the solution so that it splits the string into pairs of two characters. If the string contains an odd number of characters then it should replace the missing second character of the final pair with an underscore ('_').

Examples:
* 'abc' =>  ['ab', 'c_']
* 'abcdef' => ['ab', 'cd', 'ef']
 */
public class StringSplit {
    public static String[] solution(String s) {
        List<String> parts = new ArrayList<>();
        int length = s.length();
        for (int i = 0; i < length; i += 2) {
            parts.add(s.substring(i, Math.min(length, i + 2)));
        }
        if(!parts.isEmpty() && parts.get(parts.size()-1).length()==1){
            parts.set(parts.size()-1,parts.get(parts.size()-1)+"_");
        }
        return parts.toArray(new String[0]);
    }
}
