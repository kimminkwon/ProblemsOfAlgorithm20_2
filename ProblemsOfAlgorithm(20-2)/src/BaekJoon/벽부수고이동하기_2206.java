package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 벽부수고이동하기_2206 {

    private static class Coor {
        int x, y, length, isBreak;
        public Coor(int x, int y, int length, int isBreak) {
            this.x = x; this.y = y; this.length = length;
            this.isBreak = isBreak;
        }

        @Override
        public String toString() {
            return "(" + length + ", (" + x +
                    ", " + y + "), " + isBreak +
                    ')';
        }
    }

    private static int N, M;
    private static int[][] map;
    private static int[] dX = {-1, 1, 0, 0};
    private static int[] dY = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N + 1][M + 1];

        for(int i = 1; i <= N; i++) {
            String temp = br.readLine();
            for(int j = 1; j <= M; j++)
                map[i][j] = Character.getNumericValue(temp.charAt(j - 1));
        }
        System.out.println(findMinimumPathLength());
    }

    private static int findMinimumPathLength() {
        Queue<Coor> q = new LinkedList<>();
        q.add(new Coor(1, 1, 1, 0));
        boolean isEnd = false;
        boolean[][][] visited = new boolean[N + 1][M + 1][2];

        while(!q.isEmpty()) {
            int qLength = q.size();
            Coor c = null;
            while(--qLength >= 0) {
                c = q.poll();
                visited[c.x][c.y][c.isBreak] = true;

                if(c.x == N && c.y == M) {
                    isEnd = true;
                    return c.length;
                }
                for(int d = 0; d < 4; d++) {
                    int nX = c.x + dX[d];
                    int nY = c.y + dY[d];
                    if(isOut(nX, nY)) continue;
                    if(map[nX][nY] == 0 && !visited[nX][nY][c.isBreak]) {
                        q.offer(new Coor(nX, nY, c.length + 1, c.isBreak));
                        visited[nX][nY][c.isBreak] = true;
                    } else if(map[nX][nY] == 1 && c.isBreak == 0) {
                        q.offer(new Coor(nX, nY, c.length + 1, 1));
                        visited[nX][nY][1] = true;
                    }
                }
            }
            if(isEnd) break;
            if(q.isEmpty()) return -1;
        }
        return -1;
    }

    private static boolean isOut(int x, int y) {
        return x >= N + 1 || x < 1 || y >= M + 1 || y < 1;
    }
}
