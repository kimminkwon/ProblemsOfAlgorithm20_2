package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 행렬제곱_10830 {

    private static int N;
    private static long B;
    private static long[][] matrix;
    private static final int REMAINDER = 1000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        B = Long.parseLong(st.nextToken());
        matrix = new long[N][N];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++)
                matrix[i][j] = Long.parseLong(st.nextToken());
        }

        long[][] result = calculateMatrixPow(matrix, B);
        printResult(result);
    }

    private static long[][] calculateMatrixPow(long[][] matrix, long b) {
        if(b == 1) return divideRemainder(matrix);
        long[][] halfPowMatrix = divideRemainder(calculateMatrixPow(matrix, b / 2));
        return b % 2 == 0 ? mulMatrix(halfPowMatrix, halfPowMatrix) : mulMatrix(mulMatrix(halfPowMatrix, halfPowMatrix), divideRemainder(matrix));
    }

    private static long[][] divideRemainder(long[][] matrix) {
        for(int i = 0; i < N; i++)
            for(int j = 0; j < N; j++)
                matrix[i][j] = matrix[i][j] % REMAINDER;
        return matrix;
    }

    private static long[][] mulMatrix(long[][] matrix1, long[][] matrix2) {
        long[][] mulMatrix = new long[N][N];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                int currNum = 0;
                for(int k = 0; k < N; k++)
                    currNum += (matrix1[i][k] * matrix2[k][j] % REMAINDER);
                mulMatrix[i][j] = currNum % REMAINDER;
            }
        }
        return mulMatrix;
    }

    private static void printResult(long[][] result) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++)
                sb.append(result[i][j]).append(" ");
            sb.append("\n");
        }
        System.out.print(sb.toString());
    }
}
