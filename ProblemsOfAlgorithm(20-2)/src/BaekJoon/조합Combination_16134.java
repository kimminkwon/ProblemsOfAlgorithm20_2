package BaekJoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 조합Combination_16134 {
    private static final int MOD = 1000000007;
    private static final int N_MAX = 1000010;
    private static long[] factorial = new long[N_MAX];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        makeFactorial();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        System.out.println(combination(N, R));
    }

    private static long combination(int N, int R) {
        long partOne = factorial[N] % MOD;
        long partTwo = pow((factorial[N - R] * factorial[R]) % MOD, MOD - 2) % MOD;
        return (partOne * partTwo) % MOD;
    }

    private static long pow(long number, long p) {
        if(p == 0) return 1L;
        if(p == 1) return number % MOD;
        long halfValue = pow(number, p / 2) % MOD;
        long result = (halfValue * halfValue) % MOD;
        if(p % 2 == 0) return result % MOD;
        else return (result * number) % MOD;
    }

    private static void makeFactorial() {
        factorial[0] = 1;
        factorial[1] = 1;
        for(int i = 2; i < N_MAX; i++)
            factorial[i] = ((factorial[i - 1] % MOD) * i) % MOD;
    }
}
