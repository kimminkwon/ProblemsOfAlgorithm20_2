package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 아기상어2_17086 {

    private static class Coor {
        int x, y;
        public Coor(int x, int y) { this.x = x; this.y = y; }
    }

    private static int N, result;
    private static Coor sCoor;
    private static int[][] map;
    private static int[] dX = {-1, 0, 0, 1};
    private static int[] dY = {0, -1, 1, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }
        sharkDoEat();
    }

    private static void sharkDoEat() {
        findSharkInStart();
        int level = 2;
        int eat = 0;

        while(true) {
            if(findFoodUsingBFS(sCoor, level)) eat++;
            else break;
            if(eat == level) { eat = 0; level++; }
        }

        System.out.println(result);
    }

    private static boolean findFoodUsingBFS(Coor now, int level) {
        Queue<Coor> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];
        q.offer(now);
        int length = 0;

        while(!q.isEmpty()) {
            int depthSize = q.size();
            List<Coor> moveList = new ArrayList<>();
            while(--depthSize >= 0) {
                now = q.poll();
                visited[now.x][now.y] = true;
                if(map[now.x][now.y] > 0 && map[now.x][now.y] < level) moveList.add(now);

                for(int d = 0; d < 4; d++)
                    if(!isOut(now.x + dX[d], now.y + dY[d], level) && !visited[now.x + dX[d]][now.y + dY[d]]){
                        q.add(new Coor(now.x + dX[d], now.y + dY[d]));
                        visited[now.x + dX[d]][now.y + dY[d]] = true;
                    }
            }
            if(!moveList.isEmpty()) {
                Coor c = findFood(moveList);
                map[c.x][c.y] = 0;
                sCoor = new Coor(c.x, c.y);
                result += length;
                return true;
            } else length++;
        }
        return false;
    }

    private static Coor findFood(List<Coor> moveList) {
        Coor c = new Coor(Integer.MAX_VALUE, Integer.MIN_VALUE);
        for(Coor f : moveList) {
            if(c.x > f.x) c = f;
            else if(c.x == f.x)
                if(c.y > f.y) c = f;
        }
        return c;
    }

    private static void findSharkInStart() {
        for(int i = 0; i < N; i++)
            for(int j = 0; j < N; j++)
                if(map[i][j] == 9) {sCoor = new Coor(i, j); map[i][j] = 0;}
    }

    private static boolean isOut(int x, int y, int level) {
        if(x >= N || x < 0 || y >= N || y < 0) return true;
        else if(map[x][y] > level) return true;
        else return false;
    }
}
