package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 치즈_2636_2 {

    private static class Coor {
        int x, y;
        public Coor(int x, int y) { this.x = x; this.y = y; }
    }

    private static int N, M, remainCheese, time;
    private static int[][] map;
    private static int[] dx = {0, 1, 0, -1}, dy = {1, 0, -1, 0};

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
        meltCheese();
        System.out.println(time);
        System.out.println(remainCheese);
    }

    private static void meltCheese() {
        remainCheese = melting();
        while(true) {
            findMeltCheesePiece(); // 1. 녹을 수 있는 치즈를 찾고
            int remain = melting(); // 2. 녹이고
            time++; // 3. 시간을 증가
            if(remain <= 0) break; // 4. 남은 치즈가 없다면 끝!
            remainCheese = remain; // 5. 남은 치즈의 개수를 저장 ==> 이걸 미리한다면 출력에서 0만 출력하게 됨
        }
    }

    private static void findMeltCheesePiece() {
        Deque<Coor> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][M];
        q.offer(new Coor(0, 0));

        while(!q.isEmpty()) {
            Coor c = q.poll();
            for(int d = 0; d < 4; d++) {
                int nx = c.x + dx[d], ny = c.y + dy[d];
                if(!isOut(nx, ny) && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    if(map[nx][ny] >= 1) map[nx][ny] = 2;
                    else q.offer(new Coor(nx, ny));
                }
            }
        }
    }

    private static int melting() {
        int remain = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(map[i][j] == 2) map[i][j] = 0;
                else if(map[i][j] == 1) remain++;
            }
        }
        return remain;
    }

    private static boolean isOut(int x, int y) {
        return x >= N || x < 0 || y >= M || y < 0;
    }
}
