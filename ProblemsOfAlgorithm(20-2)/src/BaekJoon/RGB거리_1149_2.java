package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class RGB거리_1149_2 {

    private static int N, result;
    private static int[][] colorCost;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        colorCost = new int[N][3];
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            colorCost[i][0] = Integer.parseInt(st.nextToken());
            colorCost[i][1] = Integer.parseInt(st.nextToken());
            colorCost[i][2] = Integer.parseInt(st.nextToken());
        }

        findMinimumCost();
        System.out.println(result);
    }

    private static void findMinimumCost() {
        int[] redDp = new int[N]; int[] greenDp = new int[N]; int[] blueDp = new int[N];
        redDp[0] = colorCost[0][0]; greenDp[0] = colorCost[0][1]; blueDp[0] = colorCost[0][2];

        for(int i = 1; i < N; i++) {
            redDp[i] = Math.min(colorCost[i][0] + greenDp[i - 1], colorCost[i][0] + blueDp[i - 1]);
            greenDp[i] = Math.min(colorCost[i][1] + redDp[i - 1], colorCost[i][1] + blueDp[i - 1]);
            blueDp[i] = Math.min(colorCost[i][2] + redDp[i - 1], colorCost[i][2] + greenDp[i - 1]);
        }

        result = Math.min(blueDp[N - 1], Math.min(redDp[N - 1], greenDp[N - 1]));
    }
}
