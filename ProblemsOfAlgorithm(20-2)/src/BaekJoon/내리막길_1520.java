package BaekJoon;

import java.io.*;
import java.util.*;

public class 내리막길_1520 {

    private static int[][] numOfPath;
    private static int N, M;
    private static int[][] map;
    private static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};

    public static void main(String[] args)  throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        numOfPath = new int[N][M];
        for(int i = 0; i < N; i++) Arrays.fill(numOfPath[i], -1);

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }
        findPossiblePass(0, 0, new boolean[N][M]);
        System.out.println(numOfPath[0][0]);
    }

    private static int findPossiblePass(int x, int y, boolean[][] visited) {
        if(numOfPath[x][y] != -1) return numOfPath[x][y];
        if(x == N - 1 && y == M - 1) return numOfPath[x][y] = 1;

        numOfPath[x][y] = 0;
        for(int d = 0; d < 4; d++) {
            int nx = x + dx[d], ny = y + dy[d];
            if(isOut(nx, ny) || visited[nx][ny] || map[x][y] <= map[nx][ny]) continue;
            visited[nx][ny] = true;
            numOfPath[x][y] += findPossiblePass(nx, ny, visited);
            visited[nx][ny] = false;
        }
        return numOfPath[x][y];
    }

    private static boolean isOut(int x, int y) {
        return x >= N || y >= M || x < 0 || y < 0;
    }

    private static void printMap(int[][] m) {
        for(int i = 0; i < m.length; i++) {
            for(int j = 0; j < m[i].length; j++) {
                System.out.print(m[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
