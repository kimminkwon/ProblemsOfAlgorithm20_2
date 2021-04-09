package BaekJoon;

import java.io.*;
import java.util.*;

public class 빙산_2573 {

    private static class State {
        int x, y;
        public State(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static int N, M;
    private static int[][] map;
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(findMinimumTimeToDivideTwoComponent());
    }

    private static int findMinimumTimeToDivideTwoComponent() {
        int meltRes = 0, year = 0;
        while (true) {
            meltRes = isTwoComponent();
            if(meltRes == -1) return 0;
            else if(meltRes == 1) break;
            else {
                year++;
                melting();
            }
        }
        return year;
    }

    private static void melting() {
        int[][] nearSeaArr = new int[N][M];
        for(int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(map[i][j] != 0) {
                    int nearSea = 0;
                    for(int d = 0; d < 4; d++) {
                        int nx = i + dx[d], ny = j + dy[d];
                        if(isOut(nx, ny) || map[nx][ny] != 0) continue;
                        nearSea++;
                    }
                    nearSeaArr[i][j] = nearSea;
                }
            }
        }
        for(int i = 0; i < N; i++)
            for(int j = 0; j < M; j++)
                if(map[i][j] != 0)
                    map[i][j] = Math.max(0, map[i][j] - nearSeaArr[i][j]);
    }

    // -1: 다 녹았습니다 / 0: 아직 연결되어있습니다. / 1: 두개 이상 컴포넌트입니다.
    private static int isTwoComponent() {
        boolean[][] visited = new boolean[N][M];
        Deque<State> q = makeInitDeque(visited);
        if(q == null) return -1;

        while(!q.isEmpty()) {
            State s = q.poll();
            for(int d = 0; d < 4; d++) {
                int nx = s.x + dx[d], ny = s.y + dy[d];
                if(isOut(nx, ny) || visited[nx][ny] || map[nx][ny] == 0) continue;
                q.offer(new State(nx, ny));
                visited[nx][ny] = true;
            }
        }
        return isAllVisited(visited) ? 0 : 1;
    }

    private static boolean isAllVisited(boolean[][] visited) {
        for(int i = 0; i < N; i++)
            for (int j = 0; j < M; j++)
                if(map[i][j] != 0 && !visited[i][j]) return false;
        return true;
    }

    private static Deque<State> makeInitDeque(boolean[][] visited) {
        Deque<State> q = new ArrayDeque<>();
        for(int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(map[i][j] != 0) {
                    q.offer(new State(i, j));
                    visited[i][j] = true;
                    return q;
                }
            }
        }
        return null;
    }

    private static boolean isOut(int x, int y) {
        return x >= N || y >= M || x < 0 || y < 0;
    }
}
