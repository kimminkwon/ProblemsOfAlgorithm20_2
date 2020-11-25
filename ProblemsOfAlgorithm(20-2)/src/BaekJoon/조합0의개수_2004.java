package BaekJoon;

import java.util.Scanner;

public class 조합0의개수_2004 {

    private static long n;
    private static long m;
    private static long result;

    public static void main(String[] args) {
        makeInput();
        findZeroOfCombForDesc();
        printResult();
    }

    private static void printResult() {
        System.out.println(result);
    }

    private static void findZeroOfCombForDesc() {
        long zeroOfFive = findZeroOfFactorialFive(n) - findZeroOfFactorialFive(m) - findZeroOfFactorialFive(n-m);
        long zeroOfTwo = findZeroOfFactorialTwo(n) - findZeroOfFactorialTwo(m) - findZeroOfFactorialTwo(n-m);

        result = Math.min(zeroOfFive, zeroOfTwo);
    }

    private static long findZeroOfFactorialFive(long num) {
        long zeroOfFive = 0;

        for(long i = 5; i <= num; i = i * 5)
            zeroOfFive = zeroOfFive + (num / i);

        return zeroOfFive;
    }

    private static long findZeroOfFactorialTwo(long num) {
        long zeroOfTwo = 0;

        for(long i = 2; i <= num; i = i * 2)
            zeroOfTwo = zeroOfTwo + (num / i);

        return zeroOfTwo;
    }

    private static void makeInput() {
        Scanner input = new Scanner(System.in);
        n = input.nextLong();
        m = input.nextLong();
    }
}
