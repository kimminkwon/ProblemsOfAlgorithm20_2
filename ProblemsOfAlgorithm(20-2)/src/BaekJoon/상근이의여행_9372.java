package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 상근이의여행_9372 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int tc = 0; tc < T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int[][] airPlane = new int[N + 1][N + 1];
            for(int i = 0; i < N + 1; i++)
                Arrays.fill(airPlane[i], Integer.MAX_VALUE);

            for(int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                airPlane[a][b] = 1; airPlane[b][a] = 1;
            }
            sb.append(findMinimumAirplane(N, M, airPlane)).append("\n");
        }
        System.out.print(sb.toString());
    }

    private static int findMinimumAirplane(int N, int M, int[][] airPlane) {
        boolean[] visited = new boolean[N + 1]; int[] minDist = new int[N + 1]; int result = 0;
        visited[1] = true;
        int min = Integer.MAX_VALUE;
        int minIndex = 1;
        for(int i = 2; i < N + 1; i++) {
            if(airPlane[1][i] == 1) minDist[i] = 1;
            else minDist[i] = Integer.MAX_VALUE;
        }

        for(int i = 0; i < N - 1; i++) {
            for(int j = 1; j <= N; j++)
                if(min >= minDist[j] && visited[j] == false) {
                    min = minDist[j];
                    minIndex = j;
                }
            visited[minIndex] = true;
            result += min;

            for(int j = 1; j <= N; j++)
                minDist[j] = Math.min(minDist[j], airPlane[minIndex][j]);
        }
        return result;
    }
}
