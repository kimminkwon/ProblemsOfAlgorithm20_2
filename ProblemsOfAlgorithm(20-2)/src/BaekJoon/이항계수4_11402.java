package BaekJoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 이항계수4_11402 {

    private static long N, K;
    private static int M;
    private static long[] factorial;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Long.parseLong(st.nextToken());
        K = Long.parseLong(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        makeFactorial();
        System.out.println(calculateCombinationUsingPNumber());
    }

    private static long calculateCombinationUsingPNumber() {
        long result = 1;
        while(N > 0 && K > 0) {
            int n_pNumber = (int) (N % M); // N을 P진수로 바꾸었을 때 i번째 계수를 얻어낸다.
            int r_pNumber = (int) (K % M); // R을 P진수로 바꾸었을 때 i번째 계수를 얻어낸다.

            if(n_pNumber < r_pNumber) return 0; // r이 더 커진다면 0을 리턴한다.
            long currResult = calculateCombination(n_pNumber, r_pNumber) % M; // P진수의 i번째 계수 값을 통해 조합을 계산한다.
            result = (result * currResult) % M; // 해당 값을 결과에 곱해준다.

            N = N / M; K = K / M;
        }
        return result % M;
    }

    private static long calculateCombination(int N, int K) {
        long Numerator = factorial[N] % M; // 분모
        long denominator = pow((factorial[K] * factorial[N - K]) % M, M - 2) % M;
        return (Numerator * denominator) % M;
    }

    private static long pow(long number, int pow) {
        if(pow == 1) return number % M;
        long halfValue = pow(number, pow / 2) % M;
        long result = (halfValue * halfValue) % M;
        if(pow % 2 == 0) return result;
        else return (result * number) % M;
    }

    private static long[] makeFactorial() {
        factorial = new long[M + 1];
        factorial[0] = 1; factorial[1] = 1;
        for(int i = 2; i < M + 1; i++)
            factorial[i] = ((factorial[i - 1] % M) * i) % M;
        return factorial;
    }

}
