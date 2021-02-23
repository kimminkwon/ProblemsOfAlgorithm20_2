package BaekJoon;

import java.io.*;
import java.util.*;

public class 반복수열_2331 {

    private static String A;
    private static int P;
    private static Map<String, Integer> hashMap = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = st.nextToken();
        P = Integer.parseInt(st.nextToken());
        System.out.println(findRemainNumbersInProgression());
    }

    private static int findRemainNumbersInProgression() {
        String currNum = A;
        int size = 1;
        while(!hashMap.containsKey(currNum)) {
            hashMap.put(currNum, size++);
            currNum = makeNextNum(currNum);
        }
        return hashMap.get(currNum) - 1;
    }

    private static String makeNextNum(String num) {
        int n = 0;
        for(char c : num.toCharArray())
            n += Math.pow(Character.getNumericValue(c), P);
        return String.valueOf(n);
    }
}
