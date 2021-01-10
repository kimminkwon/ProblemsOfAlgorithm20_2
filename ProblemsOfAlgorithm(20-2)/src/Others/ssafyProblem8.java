package Others;

import java.util.Scanner;

/*
과제4: (어려움) 배열에 정수(음수포함)들이 저장되어있다.
배열의 일부 값들을 골라서 배열에 있는 순서대로 보면 증가하는 순서가 될 수 있다.
이러한 것들 중 가장 긴 것을 찾는 프로그램을 작성하라.

[Solve]
가장 길게 증가하는 계단식 값을 찾는 문제이다. LIS 문제!
dp[i] = i를 끝으로 하는 가장 긴 계단식 값일 때
dp[i]는 i 이전의 dp[k] 중
1) 원소 값이 i번째 원소보다 작고
2) dp[k]가 현재 dp[i]보다 큰 것을 골라서
1을 더해주면 된다.
즉, arr[i] = 50일 때, 이전에 dp중 가장 큰 값이 30이고 인덱스가 k라면
dp[k] = 30을 마지막으로 하는 가장 긴 증가 수열
~이므로, 30 다음에 50을 잇는게 제일 현명한 것이다.

해당 dp 배열중 가장 큰 값을 반환하면 된다.
 */

public class ssafyProblem8 {

    private static int n;
    private static int[] arr;
    private static int result;

    public static void main(String[] args) {
        makeInput();
        makeLIS();
        printResult();
    }

    private static void printResult() {
        System.out.println(result);
    }

    private static void makeLIS() {
        result = Integer.MIN_VALUE;
        int[] dpLisList = new int[n + 1];

        for(int i = 1; i < n + 1; i++) {
            dpLisList[i] = 1;
            for(int j = 1; j < i; j++) {
                if(arr[j] < arr[i] && dpLisList[i] < dpLisList[j] + 1) {
                    dpLisList[i] = dpLisList[j] + 1;
                }
            }
            result = result < dpLisList[i] ? dpLisList[i] : result;
        }
    }

    private static void makeInput() {
        Scanner input = new Scanner(System.in);
        n = input.nextInt();
        arr = new int[n + 1];
        for(int i = 1; i < n + 1; i++) {
            arr[i] = input.nextInt();
        }
    }
}
