package BaekJoon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class 약수_1037 {

    private static int n, result;
    private static List<Integer> divisors;

    public static void main(String[] args) {
        makeInput();
        findNumber();
        printResult();
    }

    private static void printResult() {
        System.out.println(result);
    }

    private static void findNumber() {
        Collections.sort(divisors);
        result = n == 1? (int) Math.pow(divisors.get(0), 2) : divisors.get(0) * divisors.get(n-1);
    }

    private static void makeInput() {
        Scanner input = new Scanner(System.in);
        divisors = new ArrayList<>();
        n = input.nextInt();
        for(int i = 0; i < n; i++)
            divisors.add(input.nextInt());
    }
}
