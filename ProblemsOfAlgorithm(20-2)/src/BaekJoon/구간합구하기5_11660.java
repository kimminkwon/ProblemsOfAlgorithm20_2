package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 구간합구하기5_11660 {

    private static long[][] numArr;
    private static long[][] prefixSum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        numArr = new long[N][N];
        prefixSum = new long[N][N];

        makeNumArr(N, br);
        makePrefixArr(N);

        int M = Integer.parseInt(st.nextToken());
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            System.out.println(calculatePrefix(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1,
                    Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1));
        }
    }

    private static long calculatePrefix(int x1, int y1, int x2, int y2) {
        long result = prefixSum[x2][y2];
        if(y1 > 0) result -= prefixSum[x2][y1 - 1];
        if(x1 > 0) result -= prefixSum[x1 - 1][y2];
        if(x1 > 0 && y1 > 0) result += prefixSum[x1 - 1][y1 - 1];

        return result;
    }


    private static void makePrefixArr(int N) {
        prefixSum[0][0] = numArr[0][0];
        for(int i = 1; i < N; i++)
            prefixSum[0][i] = prefixSum[0][i-1] + numArr[0][i];

        for(int i = 1; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(j == 0) prefixSum[i][j] = prefixSum[i-1][j] + numArr[i][j];
                else prefixSum[i][j] = prefixSum[i-1][j] + prefixSum[i][j - 1] - prefixSum[i - 1][j - 1] + numArr[i][j];
            }
        }
    }

    private static void makeNumArr(int N, BufferedReader br) throws IOException {
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++)
                numArr[i][j] = Integer.parseInt(st.nextToken());
        }
    }

    static void show(long[][] arr) {
        for(int i = 0; i < arr.length; i++) {
            for(int j = 0; j < arr.length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
}
