package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 행렬곱셈순서_11049 {

    private static int N;
    private static int[][] matrix;
    private static int[][] operDp;

    public static void main(String[] args)  throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        matrix = new int[N][2];
        operDp = new int[N][N];

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            matrix[i][0] = Integer.parseInt(st.nextToken());
            matrix[i][1] = Integer.parseInt(st.nextToken());
        }
        int result = findMinimumOperationForMul(0, N - 1);
        System.out.println(result);
    }

    private static int findMinimumOperationForMul(int start, int end) {
        if(operDp[start][end] != 0) return operDp[start][end];
        if(start == end) return operDp[start][start] = 0;
        if(start + 1 == end) return operDp[start][end] = matrix[start][0] * matrix[start][1] * matrix[end][1];

        operDp[start][end] = Integer.MAX_VALUE;
        for(int k = start; k < end; k++) {
            operDp[start][end] = Math.min(
                    operDp[start][end],
                    findMinimumOperationForMul(start, k) + findMinimumOperationForMul(k + 1, end)
                    + (matrix[start][0] * matrix[k][1] * matrix[end][1])
            );
        }
        return operDp[start][end];
    }
}
