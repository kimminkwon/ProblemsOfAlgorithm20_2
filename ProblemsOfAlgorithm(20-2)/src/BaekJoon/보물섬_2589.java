package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class 보물섬_2589 {
    private static class Coor {
        int x, y;

        public Coor(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "(" + x + ", " + y + ')';
        }
    }
    private static char[][] map;
    private static int W, H, result = 0;
    private static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        map = new char[H][W];
        for(int i = 0; i < H; i++)
            map[i] = br.readLine().toCharArray();

        findMaximumPath();
        System.out.println(result);
    }

    private static void findMaximumPath() {
        for(int i = 0; i < H; i++)
            for(int j = 0; j < W; j++)
                if(map[i][j] == 'L') doBFS(i, j);
    }

    private static void doBFS(int i, int j) {
        Deque<Coor> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[H][W];
        q.offer(new Coor(i, j)); visited[i][j] = true;
        int pathLength = 0;
        while(!q.isEmpty()) {
            result = Math.max(result, pathLength);
            int size = q.size();
            while(--size >= 0) {
                Coor c = q.poll();
                for(int d = 0; d < 4; d++) {
                    int nx = c.x + dx[d], ny = c.y + dy[d];
                    if(!isOut(nx, ny) && !visited[nx][ny] && map[nx][ny] == 'L') {
                        q.offer(new Coor(nx, ny));
                        visited[nx][ny] = true;
                    }
                }
            }
            pathLength++;
        }
    }

    private static boolean isOut(int x, int y) {
        return x >= H || y >= W || x < 0 || y < 0;
    }
    private static void printMap(char[][] map) {
        for(int i = 0; i < H; i++) {
            for(int j = 0; j < W; j++)
                System.out.print(map[i][j] + " ");
            System.out.println();
        }
        System.out.println();
    }
}
