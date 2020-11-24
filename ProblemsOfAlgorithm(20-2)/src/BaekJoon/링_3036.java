package BaekJoon;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class 링_3036 {

    private static List<Integer> lengthOfRings;
    private static List<String> results;

    public static void main(String[] args) {
        makeInput();
        findOtherRingsCycle();
        printResult();
    }

    private static void printResult() {
        results.stream().forEach(s -> System.out.println(s));
    }

    private static void findOtherRingsCycle() {
        lengthOfRings.stream().skip(1).forEach(ring -> results.add(calculateRingCycle(lengthOfRings.get(0), ring)));
    }

    private static String calculateRingCycle(Integer flagRing, Integer otherRing) {
        int gcdOfRings = gcd(flagRing, otherRing);
        int numerator = flagRing / gcdOfRings;
        int denominator = otherRing / gcdOfRings;

        return String.valueOf(numerator) + "/" + String.valueOf(denominator);
    }

    private static int gcd(int num1, int num2) {
        return num1 % num2 == 0 ? num2 : gcd(num2, num1%num2);
    }

    private static void makeInput() {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        lengthOfRings = new ArrayList<>();
        results = new ArrayList<>();
        IntStream.range(0, n).forEach(i -> lengthOfRings.add(input.nextInt()));
    }
}
