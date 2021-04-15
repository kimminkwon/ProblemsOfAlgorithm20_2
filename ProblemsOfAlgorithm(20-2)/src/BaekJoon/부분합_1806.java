package BaekJoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 부분합_1806 {

    private static int N, S, result;
    private static int[] number, numberSum;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        number = new int[N];
        numberSum = new int[N];
        result = Integer.MAX_VALUE;
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++)
            number[i] = Integer.parseInt(st.nextToken());
        makeNumberSum();

        findMinimumLengthForLimitSum();
        System.out.println(result == Integer.MAX_VALUE ? 0 : result);
    }

    private static void findMinimumLengthForLimitSum() {
        int left = 1; int right = N;
        while(left <= right) {
            int mid = (left + right) / 2;
            if(isPossible(mid)) { // 이 길이에서 가능한 경우: 길이를 더 짧게도 해보자!
                result = Math.min(result, mid);
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
    }

    private static boolean isPossible(int length) {
        int maxLengthSum = 0;
        for(int i = 0; i <= N - length; i++) {
            int lengthSum = 0;
            if(i == 0) lengthSum = numberSum[i + length - 1];
            else lengthSum = numberSum[i + length - 1] - numberSum[i - 1];
            maxLengthSum = Math.max(maxLengthSum, lengthSum);
        }
        return maxLengthSum >= S;
    }

    private static void makeNumberSum() {
        numberSum[0] = number[0];
        for(int i = 1; i < N; i++)
            numberSum[i] = numberSum[i - 1] + number[i];
    }
}
