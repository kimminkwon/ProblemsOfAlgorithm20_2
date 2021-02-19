package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class 토마토_7576 {

    private static class Coor {
        int x, y;
        public Coor(int x, int y) { this.x = x; this.y = y; }

        @Override
        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }

    private static int N, M, numOfRear, day;
    private static int[][] box;
    private static int[] dX = {-1, 0, 1, 0};
    private static int[] dY = {0, 1, 0, -1};

    private static void print() {
        for(int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(box[i][j] + " ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        box = new int[M][N];

        Deque<Coor> queue = new ArrayDeque<>();
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                box[i][j] = Integer.parseInt(st.nextToken());
                if(box[i][j] == 1) {
                    queue.add(new Coor(i, j));
                }
                else if(box[i][j] == 0) numOfRear++;
            }
        }
        calculateAllTomatoWelldoneDays(queue);
        System.out.println(numOfRear == 0 ? day : -1);
    }

    private static void calculateAllTomatoWelldoneDays(Deque<Coor> q) {
        while(!q.isEmpty()) {
            if(numOfRear == 0) break;
            int size = q.size();

            while(--size >= 0) {
                Coor c = q.poll();
                for(int d = 0; d < 4; d++) {
                    int nX = c.x + dX[d], nY = c.y + dY[d];
                    if(!isOut(nX, nY)) {
                        box[nX][nY] = 1;
                        q.offer(new Coor(nX, nY));
                        numOfRear--;
                    }
                }
            }
            day++;
        }
    }

    private static boolean isOut(int x, int y) {
        if(x >= M || y >= N || x < 0 || y < 0) return true;
        else if(box[x][y] != 0) return true;
        return false;
    }

}
