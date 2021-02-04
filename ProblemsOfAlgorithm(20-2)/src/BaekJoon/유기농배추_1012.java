package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
class Coo {
    private int one;
    private int two;

    public Coo(int one, int two) {
        this.one = one;
        this.two = two;
    }

    public int getOne() {
        return one;
    }

    public int getTwo() {
        return two;
    }
}

public class 유기농배추_1012 {

    private static int[] dOne = {0, 1, 0, -1};
    private static int[] dTwo = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int tc = 0; tc < T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            int[][] farm = new int[M][N];
            Coo[] cabbages = new Coo[K];
            for(int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                farm[x][y] = 1;
                cabbages[i] = new Coo(x, y);
            }
            System.out.println(findMinimumWarms(M, N, K, farm, cabbages));
        }
    }

    private static int findMinimumWarms(int M, int N, int K, int[][] farm, Coo[] cabbages) {
        int cnt = 0;
        for(Coo cabbage : cabbages) {
            if(farm[cabbage.getOne()][cabbage.getTwo()] == 0) continue;

            Queue<Coo> q = new LinkedList<>();
            q.offer(cabbage);
            while(!q.isEmpty()) {
                Coo c = q.poll();
                farm[c.getOne()][c.getTwo()] = 0;
                for(int d = 0; d < 4; d++) {
                    int nextX = c.getOne() + dOne[d]; int nextY = c.getTwo() + dTwo[d];
                    if(!isOut(nextX, nextY, M, N) && farm[nextX][nextY] == 1) {
                        q.offer(new Coo(nextX, nextY));
                        farm[nextX][nextY] = 0;
                    }
                }
            }
            cnt++;
        }
        return cnt;
    }

    private static boolean isOut(int one, int two, int M, int N) {
        return one >= M || one < 0 || two >= N || two < 0 ? true : false;
    }
}
