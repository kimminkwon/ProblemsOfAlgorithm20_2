package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 스티커_9465 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for(int tc = 0; tc < T; tc++) {
            int N = Integer.parseInt(br.readLine());
            int[][] scores = new int[2][N];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N; i++) scores[0][i] = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N; i++) scores[1][i] = Integer.parseInt(st.nextToken());
            sb.append(findMaximumProfit(N, scores)).append("\n");
        }
        System.out.print(sb.toString());
    }

    private static int findMaximumProfit(int N, int[][] scores) {
        int[][] scoreDP = new int[2][N];
        int max = 0;
        scoreDP[0][0] = scores[0][0];
        max = Math.max(max, scoreDP[0][0]);
        scoreDP[1][0] = scores[1][0];
        max = Math.max(max, scoreDP[1][0]);
        if(N > 1) {
            scoreDP[0][1] = scores[1][0] + scores[0][1];
            max = Math.max(max, scoreDP[0][1]);
            scoreDP[1][1] = scores[0][0] + scores[1][1];
            max = Math.max(max, scoreDP[1][1]);
        }
        for(int i = 2; i < N; i++) {
            scoreDP[0][i] = Math.max(scoreDP[1][i - 1], Math.max(scoreDP[1][i - 2], scoreDP[0][i - 2])) + scores[0][i];
            max = Math.max(max, scoreDP[0][i]);
            scoreDP[1][i] = Math.max(scoreDP[0][i - 1], Math.max(scoreDP[0][i - 2], scoreDP[1][i - 2])) + scores[1][i];
            max = Math.max(max, scoreDP[1][i]);
        }
        return max;
    }

}
