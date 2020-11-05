package BaekJoon;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class 소인수분해_11653 {

    private static int n;
    private static List<Integer> results;
    private static List<Integer> primes;

    public static void main(String[] args) {
        makeInput();
        makePrimeNumbers();
        makePrimeFactorization();
        printResult();
    }

    private static void makePrimeFactorization() {
        while (n > 1) {
            primes.stream().filter(p -> (n % p == 0)).findFirst().ifPresentOrElse(
                    i -> { results.add(i); n = n / i; },
                    () -> { results.add(n); n = n / n; }
            );
        }
    }

    private static void printResult() {
        results.forEach(i -> System.out.println(i));
    }

    private static void makePrimeNumbers() {
        IntStream.range(2, (int) Math.sqrt(n) + 1).filter(i -> isPrimeNumber(i)).forEach(p -> primes.add(p));
    }

    private static boolean isPrimeNumber(int number) {
        return !IntStream.range(2, (int) Math.sqrt(number) + 1).filter(i -> number % i == 0).findFirst().isPresent();
    }

    private static void makeInput() {
        initVariables();
        Scanner input = new Scanner(System.in);
        n = input.nextInt();
    }

    private static void initVariables() {
        results = new ArrayList<>();
        primes = new ArrayList<>();
    }
}
