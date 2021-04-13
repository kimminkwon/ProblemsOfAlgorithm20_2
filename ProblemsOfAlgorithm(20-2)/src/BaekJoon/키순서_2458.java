package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 키순서_2458 {

    private static final int INF = 1000000000;
    private static int N, K;
    private static int[][] minPath;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        minPath = new int[N][N];

        for(int i = 0; i < N; i++) Arrays.fill(minPath[i], INF);
        for(int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            minPath[from][to] = 1;
        }
        System.out.println(findPossiblePathForAllPair());
    }

    private static int findPossiblePathForAllPair() {
        for(int k = 0; k < N; k++)
            for(int i = 0; i < N; i++)
                for(int j = 0; j < N; j++)
                    if(i != j && i != k && j != k) minPath[i][j] = Math.min(minPath[i][j], minPath[i][k] + minPath[k][j]);

        int cnt = 0;
        for(int i = 0; i < N; i++) { // i번째 사람이
            boolean isPossible = true;
            for(int j = 0; j < N; j++) { // j번째 사람과 연관을 갖는가?
                if(minPath[i][j] == INF && minPath[j][i] == INF && i != j) {
                    isPossible = false;
                    break;
                }
            }
            if(isPossible) cnt++;
        }
        return cnt;
    }
}

