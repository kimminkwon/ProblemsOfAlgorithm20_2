package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class 플로이드_11404 {

    private static final int INF = 200000000;
    private static int N, M;
    private static int[][] adjArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        adjArr = new int[N][N];
        for(int i = 0; i < N; i++) Arrays.fill(adjArr[i], INF);

        M = Integer.parseInt(br.readLine());

        for(int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            int weight = Integer.parseInt(st.nextToken());
            adjArr[from][to] = Math.min(adjArr[from][to], weight);
        }
        for(int k = 0; k < N; k++)
            for(int i = 0; i < N; i++)
                for(int j = 0; j < N; j++)
                    if(i != j && i != k && j != k) adjArr[i][j] = Math.min(adjArr[i][j], adjArr[i][k] + adjArr[k][j]);

        printArr(adjArr);
    }

    private static void printArr(int[][] adjArr) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < adjArr.length; i++){
            for(int j = 0; j < adjArr[i].length; j++) {
                sb.append(adjArr[i][j] == INF ? "0 " : adjArr[i][j] + " ");
            }
            sb.append("\n");
        }
        System.out.print(sb.toString());
    }
}
