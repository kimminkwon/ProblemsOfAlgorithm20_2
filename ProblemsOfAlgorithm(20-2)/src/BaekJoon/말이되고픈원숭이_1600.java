package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class 말이되고픈원숭이_1600 {

    private static class Coor {
        int x, y, remain;
        public Coor(int x, int y, int remain) { this.x = x; this.y = y; this.remain = remain; }

        @Override
        public String toString() {
            return "(" + x + ", " + y + "): K = " + remain;
        }
    }

    private static int K, W, H;
    private static int result = 0;
    private static int[][] map;
    private static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};
    private static int[] dhx = {-2, -1, 1, 2, 2, 1, -1, -2}, dhy = {-1, -2, -2, -1, 1, 2, 2, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new int[H][W];

        for(int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < W; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        findMimimumPathLength();
        System.out.println(result == Integer.MAX_VALUE ? -1 : result);
    }

    private static void findMimimumPathLength() {
        Deque<Coor> q = new ArrayDeque<>();
        q.offer(new Coor(0, 0, K));
        boolean[][][] visited = new boolean[K + 1][H][W];

        while (!q.isEmpty()) {
            int size = q.size();
            while(--size >= 0) {
                Coor c = q.poll();
                if(c.x == H - 1 && c.y == W - 1) return;
                for(int d = 0; d < 4; d++) {
                    int nx = c.x + dx[d], ny = c.y + dy[d];
                    if(isOut(nx, ny) || map[nx][ny] == 1 || visited[c.remain][nx][ny]) continue;
                    q.offer(new Coor(nx, ny, c.remain));
                    visited[c.remain][nx][ny] = true;
                }
                if(c.remain <= 0) continue;
                for(int d = 0; d < 8; d++) {
                    int nx = c.x + dhx[d], ny = c.y + dhy[d];
                    if(isOut(nx, ny) || map[nx][ny] == 1 || visited[c.remain - 1][nx][ny]) continue;
                    q.offer(new Coor(nx, ny, c.remain - 1));
                    visited[c.remain - 1][nx][ny] = true;
                }
            }
            result++;
        }
        result = -1;
    }

    private static boolean isOut(int x, int y) {
        return x >= H || y >= W || x < 0 || y < 0;
    }
}
