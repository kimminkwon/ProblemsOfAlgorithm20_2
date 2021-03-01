package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 이항계수3_11401 {

    private static int N, K;
    private static long[] factorial;
    private static final long REMAINDER = 1000000007;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        factorial = new long[N + 1];
        makeFactorial(N);
        System.out.println(K == 0 || N == 1 ? 1 : calculateCombination() % REMAINDER);
    }

    private static long calculateCombination() {
        long Numerator = factorial[N];
        long denominator = calculatePow((factorial[K] * factorial[N-K]) % REMAINDER, REMAINDER - 2) % REMAINDER;
        return (Numerator % REMAINDER) * (denominator % REMAINDER);
    }

    private static void makeFactorial(int n) {
        factorial[1] = 1;
        for(int i = 2; i < N + 1; i++)
            factorial[i] = ((factorial[i - 1] % REMAINDER) * i) % REMAINDER;
    }

    private static long calculatePow(long a, long b) {
        if(b == 1) return a % REMAINDER;
        long halfValue = calculatePow(a, b / 2);
        return b % 2 == 0 ? ((halfValue % REMAINDER * halfValue % REMAINDER) % REMAINDER) : ((halfValue % REMAINDER * halfValue % REMAINDER) * (a % REMAINDER)) % REMAINDER;
    }
}
