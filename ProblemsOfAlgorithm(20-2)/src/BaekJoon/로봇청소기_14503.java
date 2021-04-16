package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 로봇청소기_14503 {

    private static class State {
        int x, y;
        public State(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    private static int N, M;
    private static int[][] map;
    private static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
    private static int r, c, dir;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        map = new int[N][M];

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        dir = Integer.parseInt(st.nextToken());

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        System.out.println(doCleaner());
    }

    private static int doCleaner() {
        int count = 0;

        while(true) {
            // 1. 현재 위치를 청소한다.
            if(map[r][c] == 0) {
                map[r][c] = -1;
                count++;
            }
            boolean isGo = false;
            // 2. 현재 위치에서 현재 방향을 기준으로 왼쪽방향부터 차례대로 탐색을 진행한다.
            for(int i = 0; i < 4; i++) {
                int nx = r + dx[getLeft(dir)], ny = c + dy[getLeft(dir)];
                if(!isOut(nx, ny) && map[nx][ny] == 0) {
                    isGo = true;
                    r = nx; c = ny; dir = getLeft(dir);
                    break;
                }
                dir = getLeft(dir);
            }
            if(!isGo) { // 4방에 모두 갈수 있는 곳이 없었다.
                int nx = r + dx[getBack(dir)], ny = c + dy[getBack(dir)];
                if(isOut(nx, ny) || map[nx][ny] == 1) break;
                else {
                    r = nx; c = ny;
                }
            }
        }
        return count;
    }

    private static boolean isOut(int r, int c) {
        return r >= N || c >= M || r < 0 || c < 0;
    }
    private static int getBack(int dir) {
        switch (dir) {
            case 0: return 2;
            case 1: return 3;
            case 2: return 0;
            case 3: return 1;
        }
        return -1;
    }

    private static int getLeft(int dir) {
        switch (dir) {
            case 0: return 3;
            case 1: return 0;
            case 2: return 1;
            case 3: return 2;
        }
        return -1;
    }
}
