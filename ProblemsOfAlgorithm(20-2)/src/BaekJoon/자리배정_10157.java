package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 자리배정_10157 {
    private static int C, R, K, count, dir;
    private static int[][] map;
    private static int[] dX = {0, -1, 0, 1};
    private static int[] dY = {1, 0, -1, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(br.readLine());

        map = new int[R][C];
        fillMapForSitNumber(0, -1);
        System.out.println(findSomePersonNumber());
    }

    private static void print(int[][] arr) {
        for(int i = 0; i < arr.length; i++) {
            for(int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static String findSomePersonNumber() {
        for(int i = 1; i <= R; i++) {
            for(int j = 1; j <= C; j++) {
                if(map[i - 1][j - 1] == K) return i + " " + j;
            }
        }
        return "0";
    }

    private static void fillMapForSitNumber(int x, int y) {
        int turnCount = 0;
        while(true) {
            int nX = x + dX[dir];
            int nY = y + dY[dir];
            if(isStop(nX, nY)) {
                if(turnCount > 4) break;
                turn();
                turnCount++;
                continue;
            } else turnCount = 0;
            map[nX][nY] = ++count;
            x = nX;
            y = nY;
        }
    }

    private static void turn() {
        dir++;
        if(dir == 4) dir = 0;
    }
    private static boolean isStop(int x, int y) {
        if(x >= R || x < 0 || y >= C || y < 0) return true;
        else if(map[x][y] != 0) return true;
        else return false;
    }
}
