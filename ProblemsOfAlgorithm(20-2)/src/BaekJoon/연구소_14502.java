package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 연구소_14502 {

    private static class Coor {
        int x, y;
        public Coor(int x, int y) { this.x = x; this.y = y; }

        @Override
        public String toString() {
            return "(" + x +
                    ", " + y +
                    ')';
        }
    }

    private static int N, M, wallSize, result;
    private static int[][] map;
    private static List<Coor> wallList;
    private static List<int[]> combOfWall;
    private static int[] dX = {-1, 1, 0, 0};
    private static int[] dY = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        wallList = new ArrayList<>();
        combOfWall = new ArrayList<>();

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 0) wallList.add(new Coor(i, j));
            }
        }
        wallSize = wallList.size();
        makeCombinationOfWall(0, 0, new int[3]);

        for(int i = 0; i < combOfWall.size(); i++) {
            calculateMaximumSafeArea(makeNewMap(combOfWall.get(i)));
        }
        System.out.println(result);
    }

    private static void calculateMaximumSafeArea(int[][] newMap) {
        int subRes = 0;
        for(int i = 0; i < N; i++)
            for (int j = 0; j < M; j++)
                if(newMap[i][j] == 2)
                    makeInfestArea(new Coor(i, j), newMap);

        for(int i = 0; i < N; i++)
            for (int j = 0; j < M; j++)
                if(newMap[i][j] == 0) subRes++;

        result = Math.max(result, subRes);
    }

    private static void makeInfestArea(Coor start, int[][] newMap) {
        boolean[][] visited = new boolean[N][M];
        Queue<Coor> q = new LinkedList<>();
        q.add(start);
        while(!q.isEmpty()) {
            Coor currC = q.poll();
            visited[currC.x][currC.y] = true;
            if(newMap[currC.x][currC.y] == 0) newMap[currC.x][currC.y] = 2;

            for(int d = 0; d < 4; d++) {
                int nX = currC.x + dX[d];
                int nY = currC.y + dY[d];
                if(!isOut(nX, nY) && newMap[nX][nY] == 0){
                    q.add(new Coor(nX, nY));
                    visited[nX][nY] = true;
                }
            }
        }
    }

    private static int[][] makeNewMap(int[] walls) {
        int[][] newMap = new int[N][M];
        Coor w1 = wallList.get(walls[0]);
        Coor w2 = wallList.get(walls[1]);
        Coor w3 = wallList.get(walls[2]);

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                newMap[i][j] = map[i][j];
                if(i == w1.x && j == w1.y) newMap[i][j] = 1;
                if(i == w2.x && j == w2.y) newMap[i][j] = 1;
                if(i == w3.x && j == w3.y) newMap[i][j] = 1;
            }
        }
        return newMap;
    }

    private static void makeCombinationOfWall(int start, int cnt, int[] combNum) {
        if(cnt == 3) {
            combOfWall.add(combNum.clone());
            return;
        } else {
            for(int i = start; i < wallSize; i++) {
                combNum[cnt] = i;
                makeCombinationOfWall(i + 1, cnt + 1, combNum);
            }
        }
    }

    private static boolean isOut(int x, int y) {
        return x >= N || x < 0 || y >= M || y < 0;
    }
}
