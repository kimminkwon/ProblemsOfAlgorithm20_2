package BaekJoon;

import java.util.Scanner;

public class 최대공약수와최소공배수_2609 {

    private static int num1, num2;
    private static int gcd, lcm;

    public static void main(String[] args) {
        makeInput();
        findGcdAndLcm();
        printResult();
    }

    private static void printResult() {
        System.out.println(gcd);
        System.out.println(lcm);
    }

    private static void findGcdAndLcm() {
        gcd = gcd(num1, num2);
        lcm = lcm(num1, num2, gcd);
    }

    private static int lcm(int num1, int num2, int gcd) {
        return num1 * num2 / gcd;
    }

    private static int gcd(int num1, int num2) {
        return num1 % num2 == 0 ? num2 : gcd(num2, num1%num2);
    }

    private static void makeInput() {
        Scanner input = new Scanner(System.in);
        int n1 = input.nextInt();
        int n2 = input.nextInt();
        num1 = Math.max(n1, n2);
        num2 = Math.min(n1, n2);
    }
}
