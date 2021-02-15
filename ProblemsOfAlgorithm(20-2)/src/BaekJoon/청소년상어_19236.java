package BaekJoon;

import java.io.*;
import java.util.*;

public class 청소년상어_19236 {

    private static class Coor {
        int x, y, score;
        public Coor(int x, int y, int score) { this.x = x; this.y = y; this.score = score; }
    }

    private static class Fish {
        int num, dir;
        public Fish(int num, int dir) { this.num = num; this.dir = dir; }

        public void turn() {
            dir = dir + 1;
            if(dir == 9) dir = 1;
        }
    }

    private static int[] dX = {0, -1, -1, 0, 1, 1, 1, 0, -1};
    private static int[] dY = {0, 0, -1, -1, -1, 0, 1, 1, 1};
    private static Fish[][] map = new Fish[4][4];
    private static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i = 0; i < 4; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++)
                map[i][j] = new Fish(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        findMaximumEatScore(new Coor(0, 0, 0), makeMap(map), 0);
        System.out.println(result);
    }

    private static void findMaximumEatScore(Coor shark, Fish[][] map, int moveNum) {
        shark.score += map[shark.x][shark.y].num;
        int dir = map[shark.x][shark.y].dir;
        map[shark.x][shark.y].num = -1;
        result = Math.max(result, shark.score);

        moveFish(map);
        int nX = shark.x; int nY = shark.y;

        map[shark.x][shark.y].num = 0;
        while(true) {
            nX += dX[dir]; nY += dY[dir];
            if(isOut(nX, nY)) break;
            if(map[nX][nY].num <= 0) continue;
            else findMaximumEatScore(new Coor(nX, nY, shark.score), makeMap(map), moveNum + 1);
        }
    }

    private static void moveFish(Fish[][] map) {
        for(int n = 1; n < 17; n++)
            MainLoop:for(int i = 0; i < 4; i++)
                for(int j = 0; j < 4; j++) {
                    if(map[i][j].num == n) {
                        for(int k = 0; k < 8; k++) {
                            int dir = map[i][j].dir;
                            int nX = i + dX[dir];
                            int nY = j + dY[dir];
                            if(!isOut(nX, nY) && map[nX][nY].num != -1) {
                                swap(nX, nY, i, j, map);
                                break;
                            } else map[i][j].turn();
                        }
                        break MainLoop;
                    }
                }
    }

    private static void swap(int x1, int y1, int x2, int y2, Fish[][] map) {
        Fish temp = map[x1][y1];
        map[x1][y1] = map[x2][y2];
        map[x2][y2] = temp;
    }

    private static Fish[][] makeMap(Fish[][] map) {
        Fish[][] newMap = new Fish[4][4];
        for(int i = 0; i < 4; i++)
            for(int j = 0; j < 4; j++)
                newMap[i][j] = new Fish(map[i][j].num, map[i][j].dir);
        return newMap;
    }

    private static boolean isOut(int x, int y) {
        return x >= 4 || x < 0 || y >= 4 || y < 0;
    }
}
