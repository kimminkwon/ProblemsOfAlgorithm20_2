package BaekJoon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/*
w(a, b, c)

 */
public class 신나는함수실행_9184 {

    private static int[][][] dp;
    private static List<int[]> instance;

    public static void main(String[] args) {
        makeInput();
        printResult();
    }

    private static void printResult() {
        for(int[] flags : instance) {
            int a = flags[0] >= 0 ? flags[0] : 0;
            int b = flags[1] >= 0 ? flags[1] : 0;
            int c = flags[2] >= 0 ? flags[2] : 0;
            System.out.printf("w(%d, %d, %d) = %d\n", flags[0], flags[1], flags[2], getDpValue(a, b, c));
        }
    }

    private static int getDpValue(int i, int j, int k) {
        if(dp[i][j][k] != 0) return dp[i][j][k];
        else if(i <= 0 || j <= 0 || k <= 0) return 1;

        if(i > 20 | j > 20 | k > 20) dp[i][j][k] = getDpValue(20, 20, 20);
        else if(i < j && j < k) dp[i][j][k] = getDpValue(i, j, k-1) + getDpValue(i, j-1, k-1) - getDpValue(i, j-1, k);
        else dp[i][j][k] = getDpValue(i-1, j, k) + getDpValue(i-1, j-1, k) + getDpValue(i-1, j, k-1) - getDpValue(i-1, j-1, k-1);

        return dp[i][j][k];
    }

    private static void makeInput() {
        Scanner input = new Scanner(System.in);
        instance = new ArrayList<>();
        dp = new int[51][51][51];

        while(true) {
            int a = input.nextInt(); int b = input.nextInt(); int c = input.nextInt();
            if(a == -1 && b == -1 && c == -1) break;
            instance.add(new int[]{a, b, c});
        }
    }
}
