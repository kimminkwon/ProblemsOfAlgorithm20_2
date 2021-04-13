package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 끝나지않는파티_11265 {

    private static int N, M;
    private static final int INF = 1000000000;
    private static int[][] adjArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        adjArr = new int[N][N];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                adjArr[i][j] = Integer.parseInt(st.nextToken());
                if(i == j) adjArr[i][j] = INF;
            }
        }

        makeMinDistArr();
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken()) - 1;
            int B = Integer.parseInt(st.nextToken()) - 1;
            int C = Integer.parseInt(st.nextToken());

            if(adjArr[A][B] <= C) sb.append("Enjoy other party");
            else sb.append("Stay here");
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    private static void makeMinDistArr() {
        for(int k = 0; k < N; k++) {
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if(k != i && i != j && k != j)
                        adjArr[i][j] = Math.min(adjArr[i][j], adjArr[i][k] + adjArr[k][j]);
                }
            }
        }
    }
}
