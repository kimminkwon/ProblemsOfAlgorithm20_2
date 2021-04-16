package Others;

import java.util.Stack;

public class Programmers_test_2 {
    public static void main(String[] args) {
        String s = "[](){}";
        int count = 0;
        for(int i = 0; i < s.length(); i++) {
            s = cycleString(s);
            if(isCorrectP(s)) count++;
        }
        System.out.println(count);
    }

    private static String cycleString(String s) {
        String cycleS = "";
        for(int i = 1; i < s.length(); i++)
            cycleS += s.charAt(i);
        cycleS += s.charAt(0);
        return cycleS;
    }

    private static boolean isCorrectP(String s) {
        int p1Num = 0, p2Num = 0, p3Num = 0;
        for(char c : s.toCharArray()) {
            switch (c) {
                case '(':
                    p1Num++; break;
                case '{':
                    p2Num++; break;
                case '[':
                    p3Num++; break;
                case ')':
                    p1Num--; break;
                case '}':
                    p2Num--; break;
                case ']':
                    p3Num--; break;
            }
            if(p1Num < 0 || p2Num < 0 || p3Num < 0) return false;
        }
        if(p1Num == 0 && p2Num == 0 && p3Num == 0) return true;
        else return false;
    }
}
