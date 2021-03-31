package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class A와B_12904 {

    private static String S, T;
    private static boolean result;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = br.readLine();
        T = br.readLine();

        findCaseForMakeS(T, T.length());
        System.out.println(result ? 1 : 0);
    }

    private static void findCaseForMakeS(String t, int length) {
        if(length == 0) return;
        if(S.equals(t)) {
            result = true;
            return;
        }
        char lastChar = t.charAt(length - 1);
        String s = t.substring(0, length - 1);
        if(lastChar == 'A') findCaseForMakeS(s, length - 1);
        else findCaseForMakeS(new StringBuilder(s).reverse().toString(), length - 1);
    }
}
