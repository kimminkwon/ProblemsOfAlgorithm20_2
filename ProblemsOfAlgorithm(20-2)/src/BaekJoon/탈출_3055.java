package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 탈출_3055 {

    private static class Coor {
        int x, y;
        public Coor(int x, int y) { this.x = x; this.y = y; }

        @Override
        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }

    private static int R, C;
    private static char[][] map;
    private static int[] dX = {-1, 1, 0, 0};
    private static int[] dY = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        for(int i = 0; i < R; i++)
            map[i] = br.readLine().toCharArray();

        System.out.println(findMinimumLengthForHedgehog());
    }

    private static String findMinimumLengthForHedgehog() {
        Queue<Coor> hQ = findHedgehog();
        Queue<Coor> wQ = findWater();
        Coor biber = findBiber();
        boolean[][] hVisited = new boolean[R][C];
        int pathLength = 0;

        while(!hQ.isEmpty()) {
            int wQSize = wQ.size();
            while(--wQSize >= 0) {
                Coor wC = wQ.poll();
                for(int d = 0; d < 4; d++) {
                    int nX = wC.x + dX[d];
                    int nY = wC.y + dY[d];
                    if(isOut(nX, nY)) continue;
                    if(map[nX][nY] == '.' || map[nX][nY] == 'S') {
                        wQ.offer(new Coor(nX, nY));
                        map[nX][nY] = '*';
                    }
                }
            }

            int hQSize = hQ.size();
            while(--hQSize >= 0) {
                Coor hC = hQ.poll();
                if(hC.x == biber.x && hC.y == biber.y) return String.valueOf(pathLength);
                hVisited[hC.x][hC.y] = true;

                for(int d = 0; d < 4; d++) {
                    int nX = hC.x + dX[d];
                    int nY = hC.y + dY[d];
                    if(isOut(nX, nY)) continue;
                    if(map[nX][nY] != '*' && !hVisited[nX][nY]) {
                        hQ.offer(new Coor(nX, nY));
                        hVisited[nX][nY] = true;
                    }
                }
            }
            if(hQ.isEmpty()) break;
            pathLength++;
        }

        return "KAKTUS";
    }

    private static Coor findBiber() {
        for(int i = 0; i < R; i++)
            for(int j = 0; j < C; j++)
                if(map[i][j] == 'D') return new Coor(i, j);
        return null;
    }

    private static Queue<Coor> findHedgehog() {
        Queue<Coor> hQ = new LinkedList<>();
        for(int i = 0; i < R; i++)
            for(int j = 0; j < C; j++)
                if(map[i][j] == 'S') hQ.add(new Coor(i, j));

        return hQ;
    }

    private static Queue<Coor> findWater() {
        Queue<Coor> waterQ = new LinkedList<>();
        for(int i = 0; i < R; i++)
            for(int j = 0; j < C; j++)
                if(map[i][j] == '*') waterQ.add(new Coor(i, j));

        return waterQ;
    }

    private static boolean isOut(int x, int y) {
        return x >= R || x < 0 || y >= C || y < 0 || map[x][y] == 'X';
    }

    private static void print() {

        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++)
                System.out.print(map[i][j] + " ");
            System.out.println();
        }
    }
}
