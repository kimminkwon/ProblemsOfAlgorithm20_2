package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 봄버맨_16918 {
    private static class Coor {
        int x, y;
        public Coor(int x, int y) { this.x = x; this.y = y; }
    }

    private static int R, C, N;
    private static int[][] map;
    private static int[] dX = {1, -1, 0, 0};
    private static int[] dY = {0, 0, 1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        for(int i = 0; i < R; i++) {
            String box = br.readLine();
            for(int j = 0; j < C; j++)
                if(box.charAt(j) == 'O') map[i][j] = 3;
        }
        doBomberMan();
        System.out.println(printMap());
    }

    private static String printMap() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++)
                if(map[i][j] == 0) sb.append(".");
                else sb.append("O");
            sb.append("\n");
        }
        return sb.toString();
    }

    private static void doBomberMan() {
        int count = 0;
        if(count++ == N) return;
        flowTime(); // 1초 동안 아무것도 하지 않는다.
        while(true) {
            if(count++ == N) break;
            flowTime();
            makeBomb(); // 폭탄을 설치한다.
            if(count++ == N) break;
            flowTime();
        }
    }

    private static void makeBomb() {
        for(int i = 0; i < R; i++)
            for(int j = 0; j < C; j++)
                if(map[i][j] == 0) map[i][j] = 3;
    }

    private static void flowTime() {
        List<Coor> bombList = new ArrayList<>();
        for(int i = 0; i < R; i++)
            for(int j = 0; j < C; j++)
                if(map[i][j] > 0) {
                    map[i][j]--;
                    if(map[i][j] == 0) bombList.add(new Coor(i, j));
                }

        for(Coor c : bombList) {
            for (int d = 0; d < 4; d++) {
                int nX = c.x + dX[d];
                int nY = c.y + dY[d];
                if (!isOut(nX, nY) && map[nX][nY] != 0) map[nX][nY] = 0;
            }
        }
    }
    private static boolean isOut(int x, int y) {
        return x >= R || x < 0 || y >= C || y < 0;
    }

    private static void print(int[][] arr, int count) {
        System.out.println(count + "일 때 ====================================");
        for(int i = 0; i < arr.length; i++) {
            for(int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
