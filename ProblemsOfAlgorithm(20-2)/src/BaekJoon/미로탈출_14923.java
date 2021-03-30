package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class 미로탈출_14923 {
    
    private static class State {
        int x, y, destory;
        public State(int x, int y, int destory) {
            this.x = x;
            this.y = y;
            this.destory = destory;
        }

        @Override
        public String toString() {
            return "S[" +
                    "(" + x +
                    "," + y +
                    "), 파괴: " + destory +
                    ']';
        }
    }

    private static int N, M, D;
    private static int[][] map;
    private static State start, end;
    private static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        st = new StringTokenizer(br.readLine());
        start = new State(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, 0);

        st = new StringTokenizer(br.readLine());
        end = new State(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, 0);

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        findMinimumPath();
        System.out.println(D);
    }

    private static void findMinimumPath() {
        Deque<State> q = new ArrayDeque<>();
        boolean[][][] visited = new boolean[2][N][M];
        q.offer(start);
        visited[start.destory][start.x][start.y] = true;

        while(!q.isEmpty()) {
            int size = q.size();
            while (--size >= 0) {
                State s = q.poll();
                if(s.x == end.x && s.y == end.y) return;
                for(int d = 0; d < 4; d++) {
                    int nx = s.x + dx[d], ny = s.y + dy[d];
                    if(isOut(nx, ny)) continue;
                    if(!visited[s.destory][nx][ny] && map[nx][ny] == 0) {
                        q.offer(new State(nx, ny, s.destory));
                        visited[s.destory][nx][ny] = true;
                    } else if(map[nx][ny] == 1 && s.destory == 0 && !visited[1][nx][ny]) {
                        q.offer(new State(nx, ny, 1));
                        visited[1][nx][ny] = true;
                    }
                }
            }
            D++;
        }
        D = -1;
    }

    private static boolean isOut(int x, int y) {
        return x >= N || y >= M || x < 0 || y < 0;
    }
}
