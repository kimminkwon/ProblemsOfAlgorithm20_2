package Others;

import java.util.Arrays;

/*
3. 배열이 정수(음수 포함)이 저장되어있다. 연속인 구간들 중 그 합이 가장 큰 구간을 찾는 프로그램을 작성하라.
[Solve]

[0, -3, 10, -5, 6, 2, -6, 3]
dp[i] = i를 끝으로 하는 최대 합 구간
dp[i] = max(0, dp[i-1]) + arr[i]이다.
dp[0] = 0 / dp[1] = -3 / dp[2] = 10 / dp[3] = 5 / dp[4] = 11 / dp[5] = 13 / dp[6] = 7 / dp[7] = 10
최종 정답 -> dp[0..i] 중 가장 큰 값

*/
public class ssafyProblem7 {
    private static int[] numArr = {0, -3, 10, -5, 6, 2, -6, 3};

    public static void main(String[] args) {
        findMaximunRange();
    }

    private static void printMaximumRange(int[] rangeDp, int[] startedIndex) {
        int index = -1;
        int maxValue = Integer.MIN_VALUE;
        for(int i = 0; i < rangeDp.length; i++) {
            if(maxValue < rangeDp[i]) {
                maxValue = rangeDp[i];
                index = i;
            }
        }
        System.out.println("구간합 최대 값은 " + maxValue + "이고, 해당 구간은 " + startedIndex[index] + " 부터 " + index + " 까지입니다.");
    }

    private static void findMaximunRange() {
        int[] rangeDp = new int[numArr.length];
        int[] startedIndex = new int[numArr.length];

        for(int i = 0; i < numArr.length; i++) {
            if(i == 0) {
                startedIndex[i] = 0;
                rangeDp[i] = 0;
            }
            else  {
                rangeDp[i] = Math.max(0, rangeDp[i - 1]) + numArr[i];
                if(rangeDp[i - 1] < 0) {
                    startedIndex[i] = i;
                } else {
                    startedIndex[i] = startedIndex[i-1];
                }
            }
        }

        printMaximumRange(rangeDp, startedIndex);
    }
}
