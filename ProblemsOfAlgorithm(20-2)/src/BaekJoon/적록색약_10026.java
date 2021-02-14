package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 적록색약_10026 {

    private static class Coor {
        int x, y;
        public Coor(int x, int y) { this.x = x; this.y = y; }

        @Override
        public String toString() {
            return "(" + x +
                    ", " + y +
                    ')';
        }
    }

    private static int N, resultOne, resultTwo;
    private static char[][] map;
    private static int[] dX = {-1, 1, 0, 0};
    private static int[] dY = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];

        for(int i = 0; i < N; i++)
            map[i] = br.readLine().toCharArray();

        System.out.println(findNumOfArea(false) + " " + findNumOfArea(true));
    }

    private static int findNumOfArea(boolean isRedGreen) {
        boolean[][] visited = new boolean[N][N];
        int numOfArea = 0;

        for(int i = 0; i < N; i++)
            for(int j = 0; j < N; j++)
                if(!visited[i][j]) {
                    doCheckArea(visited, new Coor(i, j), isRedGreen);
                    numOfArea++;
                }

        return numOfArea;
    }

    private static void doCheckArea(boolean[][] visited, Coor start, boolean isRedGreen) {
        Queue<Coor> q = new LinkedList<>();
        q.add(start);
        visited[start.x][start.y] = true;

        while(!q.isEmpty()) {
            Coor c = q.poll();
            visited[c.x][c.y] = true;

            for(int d = 0; d < 4; d++) {
                int nX = c.x + dX[d];
                int nY = c.y + dY[d];
                if(!isOut(nX, nY) && !visited[nX][nY]) {
                    if(!isRedGreen) {
                        if(isSameArea(c, new Coor(nX, nY))) {
                            q.add(new Coor(nX, nY));
                            visited[nX][nY] = true;
                        }
                    } else {
                        if(isSameAreaInRedGreen(c, new Coor(nX, nY))) {
                            q.add(new Coor(nX, nY));
                            visited[nX][nY] = true;
                        }
                    }
                }
            }
        }
    }

    private static boolean isSameArea(Coor c, Coor nC) {
        return map[c.x][c.y] == map[nC.x][nC.y];
    }

    private static boolean isSameAreaInRedGreen(Coor c, Coor nC) {
        switch (map[c.x][c.y]) {
            case 'R':
                if(map[nC.x][nC.y] == 'R' || map[nC.x][nC.y] == 'G')
                    return true;
                break;
            case 'G':
                if(map[nC.x][nC.y] == 'G' || map[nC.x][nC.y] == 'R')
                    return true;
                break;
            case 'B':
                if(map[nC.x][nC.y] == 'B')
                    return true;
                break;
        }
        return false;
    }

    private static boolean isOut(int x, int y) {
        return x >= N || x < 0 || y >= N || y < 0;
    }
}
