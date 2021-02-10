package BaekJoon;

import java.io.*;
import java.util.*;

public class 아기상어_16236 {

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

    private static int N, M;
    private static int[][] map;
    private static int[] dX = {-1, 1, 0, 0, -1, -1, 1, 1};
    private static int[] dY = {0, 0, -1, 1, -1, 1, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }
        findMaximumSafeLength();
    }

    private static void findMaximumSafeLength() {
        int safeLength = Integer.MIN_VALUE;
        for(int i = 0; i < N; i++)
            for(int j = 0; j < M; j++)
                safeLength = Math.max(safeLength, findSafeLength(i, j));
        System.out.println(safeLength);
    }

    private static int findSafeLength(int x, int y) {
        Queue<Coor> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];
        if(map[x][y] == 1) return 0;
        int length = 0;

        q.add(new Coor(x, y));
        System.out.println(q.peek() + "에서 시작!==========================");
        while(!q.isEmpty()) {
            int depthSize = q.size();
            System.out.println("현재 큐: " + q);

            while (--depthSize >= 0) {
                Coor now = q.poll();
                if(map[now.x][now.y] == 1) return length;
                visited[now.x][now.y] = true;

                for(int d = 0; d < 8; d++)
                    if(!isOut(now.x + dX[d], now.y + dY[d]) && !visited[now.x + dX[d]][now.y + dY[d]]){
                        q.add(new Coor(now.x + dX[d], now.y + dY[d]));
                        visited[now.x + dX[d]][now.y + dY[d]] = true;
                    }
            }
            length++;
        }
        return length;
    }

    private static boolean isOut(int x, int y) {
        if(x >= N || x < 0 || y >= M || y < 0) return true;
        else return false;
    }
}
