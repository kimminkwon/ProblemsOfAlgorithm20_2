package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 미친아두이노_8972 {

    private static class Coor {
        int x, y, isDie;

        public Coor(int x, int y) {
            this.x = x;
            this.y = y;
            this.isDie = 0;
        }

        @Override
        public String toString() {
            return "(" + x +
                    ", " + y +
                    ')';
        }
    }
    private static int R, C, result;
    private static int[][] map;
    private static List<Integer> move = new ArrayList<>();
    private static Deque<Coor> mad = new ArrayDeque<>();
    private static Coor jongsu;
    private static int[] dx = {0, 1, 1, 1, 0, 0, 0, -1, -1, -1};
    private static int[] dy = {0, -1, 0, 1, -1, 0, 1, -1, 0, 1};


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new int[R][C];

        for(int i = 0; i < R; i++) {
            String box = br.readLine();
            for(int j = 0; j < C; j++) {
                char c = box.charAt(j);
                if(c == 'R') {
                    mad.offer(new Coor(i, j));
                    map[i][j]++;
                }
                else if(c == 'I') jongsu = new Coor(i, j);
            }
        }

        String box = br.readLine();
        for(int i = 0; i < box.length(); i++)
            move.add(Character.getNumericValue(box.charAt(i)));

        doMove();
    }

    private static void doMove() {
        Deque<Coor> buffer = new ArrayDeque<>();
        for(int d : move) {
            result++;
            // 1. 종수의 이동
            jongsu = new Coor(jongsu.x + dx[d], jongsu.y + dy[d]);
            if(map[jongsu.x][jongsu.y] > 0) {
                System.out.println("kraj " + result);
                return;
            }
            
            // 2. 미친 아두이노의 이동
            for(int i = 0; i < R; i++)
                for(int j = 0; j < C; j++)
                    map[i][j] = 0;

            while(!mad.isEmpty()) {
                Coor c = mad.poll();
                int madD = getCloseDist(c.x, c.y);
                int nx = c.x + dx[madD], ny = c.y + dy[madD];
                if(jongsu.x == nx && jongsu.y == ny) {
                    System.out.println("kraj " + result);
                    return;
                }
                map[nx][ny]--;
            }

            // 3. 아두이노가 겹치는가?
            for(int i = 0; i < R; i++)
                for(int j = 0; j < C; j++){
                    if(map[i][j] == -1)  {
                        map[i][j] = 1;
                        mad.offer(new Coor(i, j));
                    }
                    else if(map[i][j] < -1) map[i][j] = 0;
                }
        }
        printMap(map);
    }

    private static int getCloseDist(int x, int y) {
        int madD = 0, minDist = Integer.MAX_VALUE;
        for(int d = 1; d < 10; d++) {
            if(getDist(jongsu.x, jongsu.y, x + dx[d], y + dy[d]) < minDist) {
                minDist = getDist(jongsu.x, jongsu.y, x + dx[d], y + dy[d]);
                madD = d;
            }
        }
        return madD;
    }

    private static int getDist(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    public static void printMap(int[][] map) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                if(i == jongsu.x && j == jongsu.y) sb.append('I');
                else if(map[i][j] == 0) sb.append('.');
                else if(map[i][j] == 1) sb.append('R');

            }
            sb.append("\n");
        }
        System.out.print(sb.toString());
    }
}
