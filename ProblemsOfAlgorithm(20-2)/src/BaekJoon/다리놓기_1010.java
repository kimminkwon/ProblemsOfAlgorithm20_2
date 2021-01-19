package BaekJoon;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class 다리놓기_1010 {

    private static int t;
    private static int[] n;
    private static int[] m;
    private static long[][] pascalTriangle;

    public static void main(String[] args) {
        makeInput();
        makePascalTriangle();
        printResult();
    }

    private static void printResult() {
        IntStream.range(0, t).forEach( i -> System.out.println(pascalTriangle[m[i]][n[i]]) );
    }

    private static void makePascalTriangle() {
        for(int i = 1; i < 31; i++) {
            pascalTriangle[i][0] = 1;
            for(int j = 0; j < 31; j++) {
                if(j == 0 || i == j) pascalTriangle[i][j] = 1;
                else {
                    if(i - 1 >= 0) {
                        pascalTriangle[i][j] = pascalTriangle[i - 1][j] + pascalTriangle[i - 1][j - 1];
                    }
                }
            }
        }
    }

    private static void makeInput() {
        Scanner input = new Scanner(System.in);
        t = input.nextInt();
        n = new int[t]; m = new int[t];
        pascalTriangle = new long[31][31];
        for(int i = 0; i < t; i++) {
            n[i] = input.nextInt();
            m[i] = input.nextInt();
        }
    }
}
