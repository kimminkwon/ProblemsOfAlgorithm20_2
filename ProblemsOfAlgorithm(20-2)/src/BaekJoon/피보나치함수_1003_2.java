package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 피보나치함수_1003_2 {

    private static long[] callNumOne = new long[41];
    private static long[] callNumZero = new long[41];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        makeOneAndZeroArray();

        for(int tc = 0; tc < T; tc++) {
            int N = Integer.parseInt(br.readLine());
            System.out.println(callNumZero[N] + " " + callNumOne[N]);
        }
    }

    private static void makeOneAndZeroArray() {
        callNumOne[0] = 0; callNumOne[1] = 1;
        callNumZero[0] = 1; callNumZero[1] = 0;

        for(int i = 2; i <= 40; i++) {
            callNumOne[i] = callNumOne[i-1] + callNumOne[i-2];
            callNumZero[i] = callNumZero[i-1] + callNumZero[i-2];
        }
    }
}
