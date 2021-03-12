package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 연구소_14502_2 {

    private static class Coor {
        int x, y;

        public Coor(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Coor{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    private static int N, M, result = 0;
    private static int[][] map;
    private static List<Coor> combHash = new ArrayList<>();
    private static Deque<Coor> virus = new ArrayDeque<>();
    private static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 2) virus.offer(new Coor(i, j));
                else if(map[i][j] == 0) combHash.add(new Coor(i, j));
            }
        }

        findCaseOfBuildBlock(0, 0, new int[3]);
        System.out.println(result);
    }

    private static void findCaseOfBuildBlock(int index, int num, int[] comb) {
        if(index == 3) {
            result = Math.max(flushVirus(comb), result);
            return;
        }
        for(int i = num; i < combHash.size(); i++) {
            comb[index] = i;
            findCaseOfBuildBlock(index + 1, i + 1, comb);
        }
    }

    private static int flushVirus(int[] comb) {
        Deque<Coor> vQ = new ArrayDeque<>(virus);
        int[][] currMap = copyMap(comb);

        while(!vQ.isEmpty()) {
            Coor c = vQ.poll();
            for(int d = 0; d < 4; d++) {
                int nx = c.x + dx[d], ny = c.y + dy[d];
                if(!isOut(nx, ny) && currMap[nx][ny] == 0) {
                    currMap[nx][ny] = 2;
                    vQ.offer(new Coor(nx, ny));
                }
            }
        }
        return countSafeArea(currMap);
    }

    private static int countSafeArea(int[][] currMap) {
        int cnt = 0;
        for(int i = 0; i < N; i++)
            for (int j = 0; j < M; j++)
                if(currMap[i][j] == 0) cnt++;
        return cnt;
    }

    private static int[][] copyMap(int[] comb) {
        int[][] newMap = new int[N][M];
        for(int i = 0; i < N; i++)
            for (int j = 0; j < M; j++)
                newMap[i][j] = map[i][j];

        for(int index : comb)
            newMap[combHash.get(index).x][combHash.get(index).y] = 1;
        return newMap;
    }

    private static boolean isOut(int x, int y) {
        return x >= N || y >= M || x < 0 || y < 0;
    }

    private static void printMap(int m[][]) {
        for(int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++)
                System.out.print(m[i][j] + " ");
            System.out.println();
        }
        System.out.println();
    }
}
