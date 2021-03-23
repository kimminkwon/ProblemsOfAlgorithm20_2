package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 일로만들기_1463_3 {

    private static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        System.out.println(findMinimumOperToOne());
    }

    private static int findMinimumOperToOne() {
        int[] makeOneMinOper = new int[N + 1];
        makeOneMinOper[1] = 0;
        if(N > 1) makeOneMinOper[2] = 1;
        if(N > 2) makeOneMinOper[3] = 1;

        for(int i = 4; i <= N; i++) {
            makeOneMinOper[i] = makeOneMinOper[i - 1] + 1;
            if(i % 2 == 0) makeOneMinOper[i] = Math.min(makeOneMinOper[i / 2] + 1, makeOneMinOper[i]);
            if(i % 3 == 0) makeOneMinOper[i] = Math.min(makeOneMinOper[i / 3] + 1, makeOneMinOper[i]);
        }
        return makeOneMinOper[N];
    }
}
