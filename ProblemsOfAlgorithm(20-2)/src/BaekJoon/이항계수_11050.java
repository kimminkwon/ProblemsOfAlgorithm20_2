package BaekJoon;

import java.util.Scanner;
import java.util.stream.IntStream;

public class 이항계수_11050 {
    private static int n;
    private static int k;
    private static long result;

    public static void main(String[] args) {
        makeInput();
        calculateBinomialCoefficient();
        printResult();
    }

    private static void printResult() {
        System.out.println(result);
    }

    private static void calculateBinomialCoefficient() {
        long resultOfNumerator = 1;
        long resultOfDenominator = 1;

        for(int i = 0; i < k; i++) {
            resultOfNumerator = resultOfNumerator * (n-i);
            resultOfDenominator = resultOfDenominator * (k-i);
        }

        result = resultOfNumerator / resultOfDenominator;
    }

    private static void makeInput() {
        Scanner input = new Scanner(System.in);
        n = input.nextInt();
        k = input.nextInt();
    }

}
