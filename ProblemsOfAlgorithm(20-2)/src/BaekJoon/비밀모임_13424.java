package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 비밀모임_13424 {

    private static final int INF = 200000000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for(int tc = 0; tc < T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int[][] adjArr = new int[N][N];
            for(int i = 0; i < N; i++) {
                Arrays.fill(adjArr[i], INF);
                adjArr[i][i] = 0;
            }

            for(int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int v1 = Integer.parseInt(st.nextToken()) - 1;
                int v2 = Integer.parseInt(st.nextToken()) - 1;
                int weight = Integer.parseInt(st.nextToken());
                adjArr[v1][v2] = weight;
                adjArr[v2][v1] = weight;
            }

            int K = Integer.parseInt(br.readLine());
            int[] friends = new int[K];
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < K; i++)
                friends[i] = Integer.parseInt(st.nextToken()) - 1;

            sb.append(findMinimumTotalPath(N, M, adjArr, K, friends)).append("\n");
        }
        System.out.print(sb.toString());
    }

    private static int findMinimumTotalPath(int N, int M, int[][] adjArr, int K, int[] friends) {
        // 1. 최단 경로 구하기
        for(int k = 0; k < N; k++)
            for(int i = 0; i < N; i++)
                for(int j = 0; j < N; j++)
                    if(i != j && i != k && j != k) adjArr[i][j] = Math.min(adjArr[i][j], adjArr[i][k] + adjArr[k][j]);

        int min = INF, minIndex = -1;
        // 2. 각 정점에 대해서 거리 총합 구하기
        for(int i = 0; i < N; i++) {
            int currDist = 0;
            for(int k = 0; k < K; k++)
                currDist += adjArr[friends[k]][i];
            if(currDist < min) {
                min = currDist;
                minIndex = i;
            }
        }
        return minIndex + 1;
    }

    private static void print(int[][] arr) {
        for(int i = 0; i < arr.length; i++) {
            for(int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] == INF ? "X " : arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

}
