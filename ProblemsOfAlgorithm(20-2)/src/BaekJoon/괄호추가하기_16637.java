package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 괄호추가하기_16637 {

    private static int N, result = Integer.MIN_VALUE;
    private static String[] oper;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        String box = br.readLine();
        oper = new String[N];
        for(int i = 0; i < N; i++)
            oper[i] = String.valueOf(box.charAt(i));

        findMaximumResult(1, Integer.parseInt(oper[0]));
        System.out.println(result);
    }

    private static void findMaximumResult(int index, int beforeRes) {
        if(index == N) {
            result = Math.max(result, beforeRes);
            return;
        }
        // 1. 괄호를 묶지 않고 진행
        int currRes1 = calculate(beforeRes, oper[index], Integer.parseInt(oper[index + 1]));
        findMaximumResult(index + 2, currRes1);
        // 2. 괄호를 묶고 진행
        if(index + 4 <= N) {
            int currRes2 = calculate(beforeRes, oper[index], calculate(Integer.parseInt(oper[index + 1]), oper[index + 2], Integer.parseInt(oper[index + 3])));
            findMaximumResult(index + 4, currRes2);
        }
    }

    private static int calculate(int beforeRes, String operator, int num) {
        switch (operator) {
            case "+":
                return beforeRes + num;
            case "-":
                return beforeRes - num;
            case "*":
                return beforeRes * num;
        }
        return 0;
    }
}
