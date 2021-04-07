package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 욕심쟁이판다_1937 {

    private static int[][] numOfPath;
    private static int N;
    private static int[][] map;
    private static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};

    public static void main(String[] args)  throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        numOfPath = new int[N][N];
        for(int i = 0; i < N; i++) Arrays.fill(numOfPath[i], -1);

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }
        int maxPath = 1;
        for(int i = 0; i < N; i++)
            for(int j = 0; j < N; j++)
                maxPath = Math.max(maxPath, findPossiblePass(i, j));
        System.out.println(maxPath);
    }

    private static int findPossiblePass(int x, int y) {
        if(numOfPath[x][y] != -1) return numOfPath[x][y];
        numOfPath[x][y] = 1;
        int maxPath = 0;
        for(int d = 0; d < 4; d++) {
            int nx = x + dx[d], ny = y + dy[d];
            if(isOut(nx, ny) || map[x][y] >= map[nx][ny]) continue;
            maxPath = Math.max(maxPath, findPossiblePass(nx, ny));
        }
        return numOfPath[x][y] += maxPath;
    }

    private static boolean isOut(int x, int y) {
        return x >= N || y >= N || x < 0 || y < 0;
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
