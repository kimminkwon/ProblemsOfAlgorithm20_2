package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 일로만들기_1463_2 {
    private static int[] makeOneDP;
    public static void main(String[] args)  throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        makeOneDP = new int[N+1];
        makeOneDP[1] = 0;
        if(N > 1) makeOneDP[2] = 1;
        if(N > 2) makeOneDP[3] = 1;
        System.out.println(findMinimumOper(N));
    }

    private static int findMinimumOper(int N) {
        for(int i = 4; i <= N; i++) {
            int minusOne = makeOneDP[i - 1] + 1;
            int divideTwo = (i % 2 == 0) ? makeOneDP[i / 2] + 1 : Integer.MAX_VALUE;
            int divideThree = (i % 3 == 0) ? makeOneDP[i / 3] + 1 : Integer.MAX_VALUE;
            makeOneDP[i] = Math.min(minusOne, Math.min(divideThree, divideTwo));
        }
        return makeOneDP[N];
    }
}
