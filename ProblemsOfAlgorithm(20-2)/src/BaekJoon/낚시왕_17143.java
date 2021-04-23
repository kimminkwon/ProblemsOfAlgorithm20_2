package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class 낚시왕_17143 {

    private static class Shark {
        int x, y, speed, dir, size;
        public Shark(int x, int y, int speed, int dir, int size) {
            this.x = x;
            this.y = y;
            this.speed = speed;
            this.dir = dir;
            this.size = size;
        }

        @Override
        public String toString() {
            return "[" +
                    "(" + x +
                    ", " + y +
                    ") speed=" + speed +
                    ", dir=" + dir +
                    ", size=" + size +
                    ']';
        }
    }
    private static int R, C, M, huntSize;
    private static List<Shark> sharks;
    private static int[] dx = {0, -1, 1, 0, 0}, dy = {0, 0, 0, 1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        sharks = new ArrayList<>();
        int[][] initMap = new int[R + 1][C + 1];
        for(int i = 0; i < R + 1; i++) Arrays.fill(initMap[i], -1);

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            sharks.add(new Shark(r, c, s, d, z));
            initMap[r][c] = i;
        }
        if(M > 0) doHuntingShark(initMap);
        System.out.println(huntSize);
    }

    private static void doHuntingShark(int[][] map) {
        for(int flag = 1; flag <= C; flag++) {
            hunting(map, flag);
            map = moveSharks();
        }
    }

    private static void hunting(int[][] map, int col) {
        for(int i = 1; i <= R; i++) {
            if(map[i][col] == -1) continue;
            else {
                huntSize += sharks.get(map[i][col]).size;
                sharks.get(map[i][col]).size = -1; // 잡은 것으로 처리
                break;
            }
        }
    }

    private static int[][] moveSharks() {
        // 1. 상어가 먼저 움직이고
        for(int i = 0; i < M; i++) {
            if(sharks.get(i).size == -1) continue; // 이미 죽거나 잡힌 상어
            // 움직일 횟수를 MOD를 통해 지정
            int move = 0;
            if(sharks.get(i).dir == 1 || sharks.get(i).dir == 2) move = sharks.get(i).speed % (R * 2 - 2);
            else move = sharks.get(i).speed % (C * 2 - 2);
            moveShark(sharks.get(i), move);
        }
        // 2. 겹치는 애들을 제거한다.
        return removeOverlapShark();
    }

    private static int[][] removeOverlapShark() {
        // 제거 시, 크기가 더 큰 사이즈의 상어만 남겨야한다.
        int[][] sharkMap = new int[R + 1][C + 1]; // 현재 있는 상어의 인덱스를 저장할 배열
        for(int i = 0; i < R + 1; i++) Arrays.fill(sharkMap[i], -1);

        for(int i = 0; i < M; i++) {
            if(sharks.get(i).size == -1) continue; // 이미 죽거나 잡힌 상어
            Shark s = sharks.get(i);
            if(sharkMap[s.x][s.y] == -1) sharkMap[s.x][s.y] = i;
            else {
                Shark preS = sharks.get(sharkMap[s.x][s.y]); // 기존에 저장된 상어의 크기
                if(preS.size < s.size) { // 기존 저장된 크기보다 현재 상어가 더 크다면 잡아먹는다.
                    preS.size = -1;
                    sharkMap[s.x][s.y] = i;
                } else s.size = -1;
            }
        }
        return sharkMap;
    }

    private static void moveShark(Shark s, int move) {
        while (move > 0) {
            int nx = s.x + dx[s.dir], ny = s.y + dy[s.dir];
            if(isOut(nx, ny)) {
                s.dir = nextDir(s.dir);
                continue;
            }
            s.x = nx; s.y = ny;
            move--;
        }
    }

    private static int nextDir(int dir) {
        switch (dir) {
            case 1: return 2;
            case 2: return 1;
            case 3: return 4;
            case 4: return 3;
        }
        return -1;
    }

    private static boolean isOut(int x, int y) {
        return x <= 0 || y <= 0 || x > R || y > C;
    }
}
