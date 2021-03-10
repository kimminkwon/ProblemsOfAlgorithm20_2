package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class 불_5427 {

    private static class Coor {
        int x, y;

        public Coor(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "(" + x +
                    ", " + y +
                    ')';
        }
    }

    private static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for(int tc = 0; tc < T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int W = Integer.parseInt(st.nextToken());
            int H = Integer.parseInt(st.nextToken());

            Deque<Coor> pQ = new ArrayDeque<>(), fQ = new ArrayDeque<>();
            char[][] map = new char[H][W];
            for(int i = 0; i < H; i++) {
                String box = br.readLine();
                for(int j = 0; j < W; j++) {
                    map[i][j] = box.charAt(j);
                    if(map[i][j] == '@') pQ.offer(new Coor(i, j));
                    if(map[i][j] == '*') fQ.offer(new Coor(i, j));
                }
            }

            sb.append(isEscape(H, W, pQ, fQ, map)).append("\n");
        }
        System.out.print(sb.toString());

    }

    private static String isEscape(int h, int w, Deque<Coor> pQ, Deque<Coor> fQ, char[][] map) {
        int time = 1;
        boolean[][] pVisited = new boolean[h][w];

        while(!pQ.isEmpty() || !fQ.isEmpty()) {
            if(!fQ.isEmpty()) {
                int fqSize = fQ.size();
                while(--fqSize >= 0) {
                    Coor fc = fQ.poll();
                    for(int d = 0; d < 4; d++) {
                        int nx = fc.x + dx[d], ny = fc.y + dy[d];
                        if(!isOut(nx, ny, h, w) && map[nx][ny] != '*' && map[nx][ny] != '#') {
                            map[nx][ny] = '*';
                            fQ.offer(new Coor(nx, ny));
                        }
                    }
                }
            }
            if(!pQ.isEmpty()) {
                int pqSize = pQ.size();
                while(--pqSize >= 0) {
                    Coor pc = pQ.poll();
                    for (int d = 0; d < 4; d++) {
                        int nx = pc.x + dx[d], ny = pc.y + dy[d];
                        if (isOut(nx, ny, h, w)) return String.valueOf(time);
                        if (map[nx][ny] == '.' && !pVisited[nx][ny]) {
                            pVisited[nx][ny] = true;
                            pQ.offer(new Coor(nx, ny));
                        }
                    }
                }
            } else return "IMPOSSIBLE";
            if(pQ.isEmpty()) return "IMPOSSIBLE";
            time++;
        }
        return String.valueOf(time);
    }

    private static void printMap(int h, int w, char[][] map) {
        for(int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("======================================");
    }
    private static boolean isOut(int x, int y, int h, int w) {
        return x >= h || y >= w || x < 0 || y < 0;
    }
}
