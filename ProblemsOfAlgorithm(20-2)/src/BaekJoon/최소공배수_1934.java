package BaekJoon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class 최소공배수_1934 {

    private static int t;
    private static List<int[]> numbers;
    private static List<Integer> results;

    public static void main(String[] args) {
        makeInput();
        findLCMs();
        printResult();
    }

    private static void printResult() { results.stream().forEach(integer -> System.out.println(integer)); }

    private static void findLCMs() { numbers.stream().forEach(ints -> results.add(findLCM(ints))); }

    private static Integer findLCM(int[] nums) { return (nums[0] * nums[1]) / gcd(nums[0], nums[1]); }

    private static int gcd(int num1, int num2) {
        return num1 % num2 == 0 ? num2 : gcd(num2, num1 % num2);
    }

    private static void makeInput() {
        Scanner input = new Scanner(System.in);
        t = input.nextInt();
        numbers = new ArrayList<>();
        results = new ArrayList<>();

        for(int i = 0; i < t; i++) {
            numbers.add(new int[]{input.nextInt(), input.nextInt()});
        }
    }
}
