package BaekJoon;

import java.util.Scanner;

public class 이항계수2_11051 {

    private static int n;
    private static int k;
    private static int[][] dpOfBC;

    public static void main(String[] args) {
        makeInput();
        calculateBinomialCoefficientTwo();
        printResult();
    }

    private static void printResult() {
        System.out.println(dpOfBC[n][k]);
    }

    private static void calculateBinomialCoefficientTwo() {
        initArr();
        for(int i = 1; i < n+1; i++) {
            for(int j = 1; j < k+1; j++) {
                dpOfBC[i][j] = (dpOfBC[i - 1][j - 1] + dpOfBC[i - 1][j]) % 10007;
            }
        }
    }

    private static void initArr() {
        for(int i = 0; i < n + 1; i++) {
            dpOfBC[i][0] = 1;
        }
    }

    private static void makeInput() {
        Scanner input = new Scanner(System.in);
        n = input.nextInt();
        k = input.nextInt();
        dpOfBC = new int[n+1][k+1];
    }
}
