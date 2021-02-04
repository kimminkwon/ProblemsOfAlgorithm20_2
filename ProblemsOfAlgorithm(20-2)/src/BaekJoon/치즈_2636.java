package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 치즈_2636 {

    private static int N, M, remainCheese, time;
    private static int[][] map;
    private static int[] dOne = {0, 1, 0, -1};
    private static int[] dTwo = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        makeInput();
        meltCheese();
    }

    private static void meltCheese() {
        remainCheese = melting();
        while(true) {
            findMeltCheesePiece();
            int remain = melting();
            time++;
            if(remain <= 0) break;
            remainCheese = remain;
        }
        System.out.println(time);
        System.out.println(remainCheese);
    }

    private static void findMeltCheesePiece() {
        Queue<Coor> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];
        q.offer(new Coor(0, 0));

        while(!q.isEmpty()) {
            Coor c = q.poll();
            if(visited[c.getOne()][c.getTwo()]) continue;
            visited[c.getOne()][c.getTwo()] = true;

            for(int d = 0; d < 4; d++) {
                int nextOne = c.getOne() + dOne[d]; int nextTwo = c.getTwo() + dTwo[d];
                if(!isOut(nextOne, nextTwo) && visited[nextOne][nextTwo] == false) {
                    if(map[nextOne][nextTwo] >= 1) map[nextOne][nextTwo] = 2;
                    else q.offer(new Coor(nextOne, nextTwo));
                }
            }
        }
    }

    private static int melting() {
        int remain = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(map[i][j] == 2) map[i][j] = 0;
                else if(map[i][j] == 1) remain++;
            }
        }
        return remain;
    }

    private static boolean isOut(int one, int two) {
        if(one >= N || one < 0 || two >= M || two < 0) return true;
        return false;
    }

    private static void makeInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < M; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }
    }
}
