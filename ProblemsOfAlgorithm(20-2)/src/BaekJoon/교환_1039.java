package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 교환_1039 {

    private static char[] number;
    private static int[][] memo;
    private static int K;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        number = st.nextToken().toCharArray();
        K = Integer.parseInt(st.nextToken());
        memo = new int[K + 1][1000001];

        int result = findMaxValueForNumbers(0);
        System.out.println(result);
    }

    private static int findMaxValueForNumbers(int depth) {
        int currNum = Integer.parseInt(String.valueOf(number));
        if(depth == K) return currNum;
        if(memo[depth][currNum] != 0) return memo[depth][currNum];

        int currResult = -1;
        // 2개의 위치를 바꿀 수 있는 모든 경우를 확인
        for(int i = 0; i < number.length - 1; i++) {
            for(int j = i + 1; j < number.length; j++) {
                swap(i, j);
                if(number[0] != '0') currResult = Math.max(currResult, findMaxValueForNumbers(depth + 1));
                swap(i, j);
            }
        }
        return memo[depth][currNum] = currResult;
    }

    private static void swap(int i, int j) {
        char box = number[i];
        number[i] = number[j];
        number[j] = box;
    }
}
