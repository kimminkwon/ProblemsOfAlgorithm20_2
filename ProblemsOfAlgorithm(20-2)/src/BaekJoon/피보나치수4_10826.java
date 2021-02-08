package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class 피보나치수4_10826 {

    private static BigInteger[] memo;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        memo = new BigInteger[N + 1];
        memo[0] = new BigInteger("0");
        if(N > 0) memo[1] = new BigInteger("1");
        System.out.println(fibonachi(N));
    }

    private static BigInteger fibonachi(int n) {
        if(memo[n] == null) memo[n] = fibonachi(n - 1).add(fibonachi(n - 2));
        return memo[n];
    }
}
