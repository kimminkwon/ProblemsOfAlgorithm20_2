package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 달팽이숫자_D2_1954 {
    // 우-하-좌-상
    private static int[] dOne = {0, 1, 0, -1};
    private static int[] dTwo = {1, 0, -1, 0};
    private static int snailNum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++)
            System.out.println("#" + tc + "\n" + makeSnailNumber(Integer.parseInt(br.readLine())));

    }

    private static String makeSnailNumber(int n) {
        snailNum = 1;
        int[][] snailNumArr = new int[n][n];
        makeSnailUsingRecursive(n, snailNumArr, 0, 0, 0);
        return snailNumArrToString(n, snailNumArr);
    }

    private static void makeSnailUsingRecursive(int n, int[][] snailNumArr, int one, int two, int dir) {
        if(isEnd(n, snailNumArr, one, two)) {
            snailNumArr[one][two] = snailNum++;
            return;
        }

        while(!isOut(one + dOne[dir], two + dTwo[dir], n, snailNumArr)) {
            snailNumArr[one][two] = snailNum++;
            one += dOne[dir];
            two += dTwo[dir];
        }

        makeSnailUsingRecursive(n, snailNumArr, one, two, (dir + 1) % 4);
    }

    private static boolean isOut(int one, int two, int n, int[][] snailNumArr) {
        return one >= n || one < 0 || two >= n || two < 0 || snailNumArr[one][two] != 0? true : false;
    }

    private static boolean isEnd(int n, int[][] snailNumArr, int one, int two) {
        for(int i = 0; i < 4; i++){
            if(!isOut(one+dOne[i], two+dTwo[i], n, snailNumArr) && snailNumArr[one+dOne[i]][two+dTwo[i]] == 0) return false;
        }
        return true;
    }

    private static String snailNumArrToString(int n, int[][] snailNumArr) {
        StringBuilder sb = new StringBuilder("");
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                sb.append(snailNumArr[i][j] + " ");
            }
            if(i != n-1) sb.append("\n");
        }
        return sb.toString();
    }
}
