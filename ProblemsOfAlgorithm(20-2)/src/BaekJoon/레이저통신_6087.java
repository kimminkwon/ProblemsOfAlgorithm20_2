package BaekJoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class 레이저통신_6087 {

    private static class State {
        int x, y, dir, curve;

        public State(int x, int y, int dir, int curve) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.curve = curve;
        }

        @Override
        public String toString() {
            return "[" +
                    "(" + x +
                    ", " + y +
                    ") d=" + dir +
                    ", c=" + curve +
                    ']';
        }
    }
    private static int W, H, result = Integer.MAX_VALUE;
    private static int cx, cy;
    private static char[][] map;
    private static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};
    private static int[][] dx2 = {{0, 0}, {0, 0}, {-1, 1}, {-1, 1}};
    private static int[][] dy2 = {{-1, 1}, {-1, 1}, {0, 0}, {0, 0}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new char[H][W];

        for(int i = 0; i < H; i++) {
            String box = br.readLine();
            for(int j = 0; j < W; j++) {
                map[i][j] = box.charAt(j);
            }
        }

        findMinimumMirror();
        System.out.println(result);
    }

    private static void printMap(char[][] map) {
        for(int i = 0; i < H; i++) {
            for(int j = 0; j < W; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }


    private static void findMinimumMirror() {
        Deque<State> q = makeInitStates();
        int[][] visited = new int[H][W];
            for(int i = 0; i < H; i++) Arrays.fill(visited[i], Integer.MAX_VALUE);

        while(!q.isEmpty()) {
            State s = q.poll();
            if(map[s.x][s.y] == 'C' && (s.x != cx || s.y != cy)) result = Math.min(result, s.curve);
            for(int d = 0; d < 2; d++) {
                int nx = s.x + dx2[s.dir][d], ny = s.y + dy2[s.dir][d];
                int nextDir = getDir(dx2[s.dir][d], dy2[s.dir][d]);
                if(isOut(nx, ny) || map[nx][ny] == '*' || visited[nx][ny] < s.curve + 1) continue;
                visited[nx][ny] = s.curve + 1;
                q.offer(new State(nx, ny, nextDir, s.curve + 1));
            }
            int nx = s.x + dx[s.dir], ny = s.y + dy[s.dir];
            if(!isOut(nx, ny) && map[nx][ny] != '*' && visited[nx][ny] >= s.curve) {
                visited[nx][ny] = s.curve;
                q.offer(new State(nx, ny, s.dir, s.curve));
            }
        }
    }

    private static int getDir(int dx, int dy) {
        if(dx == -1 && dy == 0) return 0;
        else if(dx == 1 && dy == 0) return 1;
        else if(dx == 0 && dy == -1) return 2;
        else if(dx == 0 && dy == 1) return 3;
        return -1;
    }

    private static Deque<State> makeInitStates() {
        Deque<State> q = new ArrayDeque<>();
        for(int i = 0; i < H; i++) {
            for(int j = 0; j < W; j++) {
                if(map[i][j] == 'C' && q.isEmpty()) {
                    cx = i; cy = j;
                    for(int d = 0; d < 4; d++)
                        if(!isOut(i + dx[d], j + dy[d]) && map[i + dx[d]][j + dy[d]] == '.')
                            q.offer(new State(i + dx[d], j + dy[d], d, 0));
                }
            }
        }
        return q;
    }

    private static boolean isOut(int x, int y) {
        return x >= H || y >= W || x < 0 || y < 0;
    }
}
