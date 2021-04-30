package BaekJoon;

import java.io.BufferedReader;
import java.util.Scanner;

public class 오르막수_11057 {

    private static final int MAX = 1010, MOD = 10007;
    private static int[][] upNumDP = new int[MAX][10];

    public static void main(String[] args) throws Exception {
        makeUpNumDP();
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int result = 0;
        for(int i = 0; i < 10; i++) result = (result + upNumDP[N][i]) % MOD;

        System.out.println(result);
    }

    private static void makeUpNumDP() {
        for(int i = 0; i < 10; i++) upNumDP[1][i] = 1;
        for(int i = 2; i < MAX; i++) {
            int box = 0;
            for(int j = 0; j < 10; j++) {
                box = (box + upNumDP[i - 1][j]) % MOD;
                upNumDP[i][j] = box;
            }
        }
    }
}
