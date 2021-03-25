package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class 벽부수고이동하기2_14442 {

    private static class State {
        int x, y, remain;

        public State(int x, int y, int remain) {
            this.x = x;
            this.y = y;
            this.remain = remain;
        }

        @Override
        public String toString() {
            return "State{" +
                    "x=" + x +
                    ", y=" + y +
                    ", remain=" + remain +
                    '}';
        }
    }

    private static int N, M, K;
    private static int[][] map;
    private static int result = 0;
    private static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for(int i = 0; i < N; i++) {
            String box = br.readLine();
            for(int j = 0; j < M; j++)
                map[i][j] = Character.getNumericValue(box.charAt(j));
        }

        findMinimumPath();
        System.out.println(result);
    }

    private static void findMinimumPath() {
        Deque<State> q = new ArrayDeque<>();
        q.offer(new State(0, 0, K));
        boolean[][][] visited = new boolean[K + 1][N][M];

        while(!q.isEmpty()) {
            result++;
            int size = q.size();
            while(--size >= 0) {
                State s = q.poll();
                if(s.x == N - 1 && s.y == M - 1) return;
                for(int d = 0; d < 4; d++) {
                    int nx = s.x + dx[d], ny = s.y + dy[d];
                    if(isOut(nx, ny)) continue;
                    if(s.remain > 0 && map[nx][ny] == 1 && !visited[s.remain - 1][nx][ny]) {
                        q.offer(new State(nx, ny, s.remain - 1));
                        visited[s.remain - 1][nx][ny] = true;
                    } else if(map[nx][ny] == 0 && !visited[s.remain][nx][ny]) {
                        q.offer(new State(nx, ny, s.remain));
                        visited[s.remain][nx][ny] = true;
                    }
                }
            }
        }
        result = -1;
    }

    private static boolean isOut(int x, int y) {
        return x >= N || y >= M || x < 0 || y < 0;
    }

    private static void printMap(int[][] map) {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
}
