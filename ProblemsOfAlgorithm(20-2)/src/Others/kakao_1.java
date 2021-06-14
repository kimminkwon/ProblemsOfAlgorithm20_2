package Others;

import java.util.*;
import java.io.*;

public class kakao_1 {

    public static void main(String[] args) {
        String s = "2three45sixseven";


        int res = solution(s);
        System.out.println(res);
    }

    private static int solution(String s) {
        Map<String, String> numToStr = makeHashMap();
        StringBuilder result = new StringBuilder();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < s.length(); i++) {
            if(sb.length() > 0 && numToStr.containsKey(sb.toString())) {
                String currNum = sb.toString();
                result.append(numToStr.get(currNum));
                sb = new StringBuilder();
            }
            if(s.charAt(i) >= 48 && s.charAt(i) <= 57) {
                result.append(s.charAt(i));
            } else sb.append(s.charAt(i));
        }
        if(sb.length() > 0) {
            String currNum = sb.toString();
            result.append(numToStr.get(currNum));
        }
        return Integer.parseInt(result.toString());
    }

    private static Map<String,String> makeHashMap() {
        Map<String, String> numToStr =  new HashMap<>();
        numToStr.put("zero", "0");
        numToStr.put("one", "1");
        numToStr.put("two", "2");
        numToStr.put("three", "3");
        numToStr.put("four", "4");
        numToStr.put("five", "5");
        numToStr.put("six", "6");
        numToStr.put("seven", "7");
        numToStr.put("eight", "8");
        numToStr.put("nine", "9");
        return numToStr;
    }
}
