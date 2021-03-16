package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 신기한소수_2023 {
    private static int N;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        makeNumber(N, "", 0);
        System.out.print(sb.toString());
    }

    private static void makeNumber(int N, String num, int length) {
        if(length == N) {
            sb.append(num).append("\n");
            return;
        }
        for(int i = 1; i <= 9; i++) {
            String box = num + String.valueOf(i);
            if(isPrimeNumber(Integer.parseInt(box)))
                makeNumber(N, box, length + 1);
        }
    }

    private static boolean isPrimeNumber(int number) {
        if(number <= 1) return false;
        for(int i = 2; i <= Math.sqrt(number); i++)
            if(number % i == 0) return false;
        return true;
    }
}

