package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class 공주님을구해라_17836 {

    private static class State {
        int x, y, haveSword;
        public State(int x, int y, int haveSword) {
            this.x = x;
            this.y = y;
            this.haveSword = haveSword;
        }

        @Override
        public String toString() {
            return "S[" +
                    "(" + x +
                    "," + y +
                    "), 칼: " + haveSword +
                    ']';
        }
    }

    private static int N, M, T;
    private static int[][] map;
    private static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }
        System.out.println(soldierSafePrincess());
    }

    private static String soldierSafePrincess() {
        int time = 0;
        Deque<State> q = new ArrayDeque<>();
        boolean[][][] visited = new boolean[2][N][M];
        q.offer(new State(0, 0, 0));

        while(!q.isEmpty()) {
            int size = q.size();
            while(--size >= 0) {
                State s = q.poll();
                if(s.x == N - 1 && s.y == M - 1) return String.valueOf(time);
                for(int d = 0; d < 4; d++) {
                    int nx = s.x + dx[d], ny = s.y + dy[d];
                    if (isOut(nx, ny)) continue;
                    if (map[nx][ny] == 0 && !visited[s.haveSword][nx][ny]) {
                        visited[s.haveSword][nx][ny] = true;
                        q.offer(new State(nx, ny, s.haveSword));
                    } else if(map[nx][ny] == 2 && !visited[s.haveSword][nx][ny]) {
                        visited[1][nx][ny] = true;
                        q.offer(new State(nx, ny, 1));
                    } else if(map[nx][ny] == 1 && !visited[s.haveSword][nx][ny] && s.haveSword == 1) {
                        visited[s.haveSword][nx][ny] = true;
                        q.offer(new State(nx, ny, 1));
                    }
                }
            }
            time++;
            if(time > T) break;
        }
        return "Fail";

    }

    private static boolean isOut(int x, int y) {
        return x >= N || y >= M || x < 0 || y < 0;
    }
}
