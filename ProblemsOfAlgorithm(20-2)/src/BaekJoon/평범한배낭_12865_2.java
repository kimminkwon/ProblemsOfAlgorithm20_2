package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 평범한배낭_12865_2 {

    private static class Stock {
        int w, v;

        public Stock(int w, int v) {
            this.w = w;
            this.v = v;
        }
    }

    private static int N, K;
    private static Stock[] stocks;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        stocks = new Stock[N];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            stocks[i] = new Stock(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        System.out.println(findMaximumProfit());
    }

    private static int findMaximumProfit() {
        int[][] dp = new int[N + 1][K + 1];
        for(int i = 1; i <= N; i++) {
            Stock currS = stocks[i - 1];
            for(int j = 1; j <= K; j++) {
                if(j < currS.w) {
                    dp[i][j] = dp[i - 1][j];
                } else if(j == currS.w) {
                    int case1 = dp[i - 1][j];
                    int case2 = currS.v;
                    dp[i][j] = Math.max(case1, case2);
                } else {
                    int case1 = dp[i - 1][j];
                    int case2 = dp[i][j - 1];
                    int case3 = currS.v + dp[i - 1][j - currS.w];
                    dp[i][j] = Math.max(case1, Math.max(case2, case3));
                }
            }
        }

        return dp[N][K];
    }
}
