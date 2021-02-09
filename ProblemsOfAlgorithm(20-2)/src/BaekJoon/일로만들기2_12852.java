package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 일로만들기2_12852 {
    private static int[] makeOneDP;
    private static int[] beforeNum;

    public static void main(String[] args)  throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        makeOneDP = new int[N+1]; beforeNum = new int[N+1];
        makeOneDP[1] = 0; beforeNum[1] = 0;
        if(N > 1) {
            makeOneDP[2] = 1;
            beforeNum[2] = 1;
        }
        if(N > 2) {
            makeOneDP[3] = 1;
            beforeNum[3] = 1;
        }
        System.out.println(findMinimumOper(N));
        printBeforeNum(N);
    }

    private static void printBeforeNum(int n) {
        StringBuilder sb = new StringBuilder();
        sb.append(n + " ");
        while(true) {
            if(beforeNum[n] <= 0) break;
            sb.append(beforeNum[n] + " ");
            n = beforeNum[n];
        }
        System.out.println(sb.toString());
    }

    private static int findMinimumOper(int N) {
        for(int i = 4; i <= N; i++) {
            int minusOne = makeOneDP[i - 1] + 1;
            int divideTwo = (i % 2 == 0) ? makeOneDP[i / 2] + 1 : Integer.MAX_VALUE;
            int divideThree = (i % 3 == 0) ? makeOneDP[i / 3] + 1 : Integer.MAX_VALUE;
            makeOneDP[i] = Math.min(minusOne, Math.min(divideThree, divideTwo));

            if(makeOneDP[i] == minusOne) beforeNum[i] = i - 1;
            else if(makeOneDP[i] == divideTwo) beforeNum[i] = i / 2;
            else beforeNum[i] = i / 3;
        }
        return makeOneDP[N];
    }
}
