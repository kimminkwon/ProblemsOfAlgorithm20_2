package BaekJoon;

import java.io.*;
import java.util.*;

public class PuyoPuyo_11559 {

    private static class Coor {
        int x, y;
        public Coor(int x, int y) { this.x = x; this.y = y; }

        @Override
        public String toString() {
            return "("+ x +
                    ", " + y +
                    ')';
        }
    }

    private static int result;
    private static char[][] map;
    private static char[] colors = {'R', 'G', 'B', 'P', 'Y'};
    private static int[] dX = {-1, 1, 0, 0}, dY = {0, 0, -1, 1};

    private static void print() {
        for(int i = 0; i < 12; i++) {
            for(int j = 0; j < 6; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map = new char[12][6];
        for(int i = 0; i < 12; i++)
            map[i] = br.readLine().toCharArray();

        doPuyoPyuo();
        System.out.println(result);
    }

    private static void doPuyoPyuo() {
        boolean isPop = false;
        for(int i = 0; i < 12; i++) {
            for(int j = 0; j < 6; j++) {
                if(map[i][j] != '.') {
                    List<Coor> popList = popColor(new Coor(i, j), map[i][j]);
                    if(popList.size() >= 4) {
                        doPop(popList);
                        isPop = true;
                    }
                }
            }
        }
        if(isPop) {
            for (int i = 10; i >= 0; i--)
                for (int j = 5; j >= 0; j--)
                    if (map[i][j] != '.' && map[i + 1][j] == '.') doDown(i, j);
            result++;
            doPuyoPyuo();
        }
    }

    private static void doPop(List<Coor> popList) {
        for(Coor c : popList)
            map[c.x][c.y] = '.';
    }

    private static void doDown(int x, int y) {
        while(true) {
            int nX = x + 1;
            if(isOut(nX, y) || map[nX][y] != '.') break;
            map[nX][y] = map[x][y];
            map[x][y] = '.';
            x = nX;
        }
    }

    private static List<Coor> popColor(Coor c, char color) {
        boolean[][] visited = new boolean[12][6];
        Deque<Coor> q = new ArrayDeque<>();
        q.offer(c);
        visited[c.x][c.y] = true;
        List<Coor> popList = new ArrayList<>();
        popList.add(c);
        while(!q.isEmpty()) {
            Coor cuurC = q.poll();

            for(int d = 0; d < 4; d++) {
                int nX = cuurC.x + dX[d], nY = cuurC.y + dY[d];
                if(!isOut(nX, nY) && map[nX][nY] == color && !visited[nX][nY]) {
                    visited[nX][nY] = true;
                    q.offer(new Coor(nX, nY));
                    popList.add(new Coor(nX, nY));
                }
            }
        }
        return popList;
    }

    private static boolean isOut(int x, int y) {
        return x >= 12 || x < 0 || y >= 6 || y < 0;
    }
}
