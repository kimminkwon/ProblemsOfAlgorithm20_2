package BaekJoon;

import java.io.*;
import java.util.*;

public class 가장큰정사각형_1915 {

    private static int N, M;
    private static int[][] map;

    public static void main(String[] args)  throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for(int i = 0; i < N; i++) {
            String box = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = Character.getNumericValue(box.charAt(j));
            }
        }

        for(int i = 1; i < N; i++)
            for(int j = 1; j < M; j++)
                if(map[i][j] == 1) {
                    int minArea = Math.min(map[i - 1][j], Math.min(map[i][j - 1], map[i - 1][j - 1]));
                    map[i][j] = minArea + 1;
                }

        int maxSize = 0;
        for(int i = 0; i < N; i++)
            for(int j = 0; j < M; j++)
                maxSize = Math.max(map[i][j], maxSize);

        System.out.println(maxSize * maxSize);
    }
}
