package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 택배_1719 {

    private static final int INF = 200000000;
    private static int N, M;
    private static int[][] adjArr, minVertex;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        adjArr = new int[N][N];
        minVertex = new int[N][N];
        for(int i = 0; i < N; i++) {
            Arrays.fill(adjArr[i], INF);
            Arrays.fill(minVertex[i], INF);
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            int weight = Integer.parseInt(st.nextToken());
            adjArr[from][to] = weight;
            adjArr[to][from] = weight;

            minVertex[from][to] = to;
            minVertex[to][from] = from;
        }

        printArr(adjArr);
        printArr(minVertex);

        findFirstStopoverVertex();
        printArr(adjArr);
        printResult(minVertex);
    }

    private static void findFirstStopoverVertex() {
        for(int k = 0; k < N; k++) {
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if(i != j && i != k && j != k) {
                        if(adjArr[i][j] > adjArr[i][k] + adjArr[k][j]) {
                            adjArr[i][j] = adjArr[i][k] + adjArr[k][j];
                            minVertex[i][j] = minVertex[i][k];
                        }
                    }
                }
            }
        }

    }

    private static void printResult(int[][] map) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                sb.append(map[i][j] == INF ? "-" : map[i][j] + 1).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb.toString());
    }

    private static void printArr(int[][] map) {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                System.out.print(map[i][j] == INF ? "X " : map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
