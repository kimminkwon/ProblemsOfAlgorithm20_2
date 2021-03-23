package Programmers;

public class Level3_N으로표현 {
    private static int result = Integer.MAX_VALUE;

    public static void main(String[] args) {
        int N = 5, number = 31168;
        int answer = 0;

        findMinimumUsingNumber(N, number, 0, 0);
        System.out.println(result == Integer.MAX_VALUE ? -1 : result);
    }

    private static void findMinimumUsingNumber(int N, int number, int index, int value) {
        if(number == value) result = Math.min(index, result);
        if(index == 8) return;

        int n = N;
        for(int i = 1; i <= 8 - index; i++) {
            findMinimumUsingNumber(N, number, index + i, value + n);
            findMinimumUsingNumber(N, number, index + i, value - n);
            findMinimumUsingNumber(N, number, index + i, value / n);
            findMinimumUsingNumber(N, number, index + i, value * n);
            n = Integer.parseInt(String.valueOf(n) + String.valueOf(N));
        }
    }
}
