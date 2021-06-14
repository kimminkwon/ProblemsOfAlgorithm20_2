package BaekJoon;

import java.awt.print.Pageable;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 동전2_2294 {

    private static int n, k;
    private static int[] coins;
    private static final int MAX = 1000000000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        coins = new int[n];

        for(int i = 0; i < n; i++)
            coins[i] = Integer.parseInt(br.readLine());

        System.out.println(findMinimumCoin());
    }

    private static int findMinimumCoin() {
        Arrays.sort(coins);
        int[] coinDp = new int[k + 1]; // arr[n][k] = n개까지 동전을 고려할 때, 합이 k가 되는 최소 개수

        Arrays.fill(coinDp, MAX);

        for(int i = 1; i < n + 1; i++) {
            int currCoin = coins[i - 1];
            for(int j = currCoin; j < k + 1; j++) {
                if(j == currCoin) coinDp[j] = 1; // 이 경우 반드시 하나의 코인만 사용할 수 있다.
                else coinDp[j] = Math.min(coinDp[j - currCoin] + 1, coinDp[j]);
            }
        }
        return coinDp[k] == MAX ? -1 : coinDp[k];
    }
}
