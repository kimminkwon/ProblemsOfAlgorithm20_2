package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 동전1_2239 {

    private static int N, K;
    private static int[] coins, coinDP;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        coins = new int[N];
        coinDP = new int[K + 1];
        for(int i = 0; i < N; i++)
            coins[i] = Integer.parseInt(br.readLine());

        System.out.println(findMinimumCoinForK());
    }

    private static int findMinimumCoinForK() {
        coinDP[0] = 1;
        for(int i = 0; i < N; i++) {
            for(int k = coins[i]; k <= K; k++) {
                if(k - coins[i] >= 0) coinDP[k] += coinDP[k - coins[i]];
            }
        }
        System.out.println(Arrays.toString(coinDP));
        return coinDP[K];
    }
}
