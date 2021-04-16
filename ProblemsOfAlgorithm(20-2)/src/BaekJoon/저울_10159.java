package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 저울_10159 {

    private static int N, M;
    private static int[][] adjArr;
    private static final int INF = 200000000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        adjArr = new int[N][N];
        for(int i = 0; i < N; i++) Arrays.fill(adjArr[i], INF);
        for(int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            adjArr[from][to] = 1;
        }

        makeMinimumPathEachPair();
        printResult();
    }

    private static void printResult() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++) {
            int count = 0;
            for(int j = 0; j < N; j++)
                if(i != j && adjArr[i][j] == INF && adjArr[j][i] == INF) count++;
            sb.append(count).append("\n");
        }
        System.out.print(sb.toString());
    }

    private static void makeMinimumPathEachPair() {
        for(int k = 0; k < N; k++)
            for(int i = 0; i < N; i++)
                for(int j = 0; j < N; j++)
                    if(i != j && i != k && j != k) adjArr[i][j] = Math.min(adjArr[i][j], adjArr[i][k] + adjArr[k][j]);
    }

    private static void printMap() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++)
                sb.append(adjArr[i][j] == INF ? "X " : adjArr[i][j] + " ");
            sb.append("\n");
        }
        System.out.print(sb.toString());
    }

}
