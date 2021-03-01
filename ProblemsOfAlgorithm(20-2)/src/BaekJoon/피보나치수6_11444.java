package BaekJoon;

import java.io.*;
import java.util.*;
import java.math.BigInteger;

public class 피보나치수6_11444 {

    private static final long REMAINDER = 1000000007;
    private static long[][] flagMatrix = {{1, 1}, {1, 0}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BigInteger N = new BigInteger(br.readLine());
        long[][] result = calculateMatrixPow(flagMatrix, N);
        System.out.println(result[0][1]);
    }

    private static long[][] calculateMatrixPow(long[][] matrix, BigInteger b) {
        if(b.equals(BigInteger.valueOf(1))) return divideRemainder(matrix);
        long[][] halfPowMatrix = divideRemainder(calculateMatrixPow(matrix, b.divide(BigInteger.valueOf(2))));
        return b.remainder(BigInteger.valueOf(2)).equals(BigInteger.ZERO) ? mulMatrix(halfPowMatrix, halfPowMatrix) : mulMatrix(mulMatrix(halfPowMatrix, halfPowMatrix), divideRemainder(matrix));
    }

    private static long[][] divideRemainder(long[][] matrix) {
        for(int i = 0; i < 2; i++)
            for(int j = 0; j < 2; j++)
                matrix[i][j] = matrix[i][j] % REMAINDER;
        return matrix;
    }

    private static long[][] mulMatrix(long[][] matrix1, long[][] matrix2) {
        long[][] mulMatrix = new long[2][2];
        for(int i = 0; i < 2; i++) {
            for(int j = 0; j < 2; j++) {
                int currNum = 0;
                for(int k = 0; k < 2; k++)
                    currNum += (matrix1[i][k] * matrix2[k][j] % REMAINDER);
                mulMatrix[i][j] = currNum % REMAINDER;
            }
        }
        return mulMatrix;
    }
}
