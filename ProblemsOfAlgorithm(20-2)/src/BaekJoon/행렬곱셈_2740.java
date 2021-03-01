package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 행렬곱셈_2740 {

    private static int N, M, K;
    private static int[][] matrix1, matrix2, resMatrix;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        matrix1 = new int[N][M];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++)
                matrix1[i][j] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        st.nextToken();
        K = Integer.parseInt(st.nextToken());
        matrix2 = new int[M][K];
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < K; j++)
                matrix2[i][j] = Integer.parseInt(st.nextToken());
        }
        resMatrix = calculateMultiMatrix();
        printResult();
    }

    private static void printResult() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < K; j++)
                sb.append(resMatrix[i][j]).append(" ");
            sb.append("\n");
        }
        System.out.print(sb.toString());
    }

    private static int[][] calculateMultiMatrix() {
        int[][] result = new int[N][K];

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < K; j++) {
                int currNum = 0;
                for(int k = 0; k < M; k++)
                    currNum += matrix1[i][k] * matrix2[k][j];
                result[i][j] = currNum;
            }
        }
        return result;
    }
}
